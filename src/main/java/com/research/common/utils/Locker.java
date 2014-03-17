package com.research.common.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.research.common.mongo.MongoCollection;
import com.research.common.mongo.MongoCollectionFactory;

/**
 * 通用锁实现
 * 
 */
public class Locker {
    public static final String fmt = "yyyy/MM/dd HH:mm:ss";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(fmt);
    private String key;
    private int expiredInSeconds;
    private String ip; // 本机IP
    private LockInfo lockInfo; // 锁信息，每次check时获得最新数据
    private boolean strict = false; // 是否为严格模式，获得锁之后再查询一次，避免并发的情况

    public Locker(String key, int expiredInSeconds) {
        assert key != null;
        this.key = key;
        this.expiredInSeconds = expiredInSeconds;
        try {
            InetAddress hostAddr = this.getLocalIp();
            this.ip = hostAddr.getHostAddress();
        } catch (SocketException e) {
            throw new RuntimeException("解析主机地址失败...");
        }
    }

    public Locker strict() {
        this.strict = true;
        return this;
    }

    public boolean acquire() {
        return acquire(null);
    }

    public boolean acquire(Map<String, Object> extraData) {
        if (check()) {
            if (lockInfo == null) {
                lockInfo = new LockInfo();
            }
            lockInfo.key = key;
            lockInfo.senderIp = ip;
            lockInfo.updateTime = formatDate(new Date());
            if (extraData != null) {
                for (String field : extraData.keySet()) {
                    lockInfo.addExtra(field, extraData.get(field));
                }
            }
            saveLock(lockInfo);
            if (strict) {
                return check();
            } else {
                return true;
            }

        }

        return false;
    }

    public boolean check() {
        boolean acquire = false;
        lockInfo = findLock(key);

        if (lockInfo == null) {
            acquire = true;
        } else {
            String senderIp = lockInfo.senderIp;
            String updateTime = lockInfo.updateTime;
            Date updateDate = parseDate(updateTime);
            if (senderIp.equals(ip) || updateDate == null) {
                acquire = true; // 是本机IP,或时间格式不正确,视为得到锁
            } else {
                int interval = (int) (new Date().getTime() - updateDate.getTime()) / 1000;
                if (interval > expiredInSeconds) {
                    acquire = true;
                }
            }
        }
        return acquire;

    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    private String formatDate(Date d) {
        synchronized (sdf) {
            return sdf.format(d);
        }
    }

    private Date parseDate(String date) {
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    private LockInfo findLock(String key) {
        MongoCollection<LockInfo> lockCollection = MongoCollectionFactory.common.lock(LockInfo.class);
        return lockCollection.findOne(Query.query(Criteria.where("key").is(key)));
    }

    private void saveLock(LockInfo lockInfo) {
        MongoCollection<LockInfo> lockCollection = MongoCollectionFactory.common.lock(LockInfo.class);
        lockCollection.save(lockInfo);
    }

    private InetAddress getLocalIp() throws SocketException {
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        // find eth0 first
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            if (ni.getName() != null && ni.getName().equals("eth0")) {
                Enumeration<InetAddress> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    InetAddress ia = e2.nextElement();
                    if (ia instanceof Inet4Address) {
                        return ia;
                    }
                }
            }
        }
        // find a eth
        e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            if (ni.getName() != null && ni.getName().contains("eth")) {
                Enumeration<InetAddress> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    InetAddress ia = e2.nextElement();
                    if (ia instanceof Inet4Address) {
                        return ia;
                    }
                }
            }
        }
        throw new RuntimeException("getLocalIp() error!");
    }

    @Document(collection = "lock")
    private static class LockInfo {
    	@Field("key")
        private String id;
        @Field("key")
        private String key;
        @Field("sender_ip")
        private String senderIp;
        @Field("update_time")
        private String updateTime; // mongo中保存字符串格式，与python中统一，格式为：yyyy/MM/dd HH:mm:ss
        @Field("data")
        private Map<String, Object> data;

        public void addExtra(String field, Object value) {
            if (data == null) {
                data = new HashMap<String, Object>();
            }
            data.put(field, value);
        }
    }

    public static void main(String[] args) {
        Locker locker = new Locker("test", 100);
        Map<String, Object> extra = new HashMap<String, Object>();
        extra.put("bbc", "sss");
        locker.acquire(extra);
    }
}
