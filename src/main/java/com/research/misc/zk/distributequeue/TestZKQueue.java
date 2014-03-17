package com.research.misc.zk.distributequeue;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.server.WatchManager;

/**
 * @author yicheng
 * @since 2014年2月20日
 *
 */
public class TestZKQueue {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Watcher wc = new MyWatcher();
        Watcher wc2 = new YourWatcher();
        try {
            ZooKeeper zk = new ZooKeeper("localhost", 1000, wc); 
//            zk.getChildren("/distributequeue", wc);
//            ZooKeeper zk2 = new ZooKeeper("localhost", 1000, wc2);
//            DistributedQueue queue = new DistributedQueue(zk, "/distributequeue", null);
//            queue.offer(new byte[]{'a','b'});
//            queue.poll();
            zk.create("/nodetest", "hello world!".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            Thread.sleep(20*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static private class MyWatcher implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            System.out.println("MyWatcher get event : " + event);
        }
        
    }
    
    static private class YourWatcher implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            System.out.println("YourWatcher get event : " + event);
        }
        
    }
}
