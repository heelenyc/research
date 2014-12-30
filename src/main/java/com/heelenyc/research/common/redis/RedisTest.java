package com.heelenyc.research.common.redis;

import redis.clients.jedis.Jedis;

/**
 * @author yicheng
 * @since 2014年12月10日
 * 
 */
public class RedisTest {

    private static int[] subfix = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            // RedisDaoFactory.loadProperties("redis-datasource.properties");
            // IRedisDao redisDao =
            // RedisDaoFactory.getRedisDaoByName("redis_node_local");

            Jedis jedis = new Jedis("localhost", 6379);
            // // System.out.println("OK".equals(redisDao.set("hello",
            // "world")));
            // // System.out.println(redisDao.del("hello"));
            // long bgt = System.currentTimeMillis();
            // int totalNum = 10000 * 100;
            // for (int i = 0; i < totalNum; i++) {
            // //redisDao.zadd("masstest", subfix[i % 10], "masstest_"+
            // System.currentTimeMillis() + "_" + subfix[i % 10]);
            // redisDao.zrem("masstest","masstest_"+ i+ "_" + subfix[i % 10]);
            // // redisDao.zadd("masstest", i, "masstest_"+ i+ "_" + subfix[i %
            // 10]);
            // }
            // System.out.println("spent time in ms : " +
            // (System.currentTimeMillis() - bgt));
            // System.out.println(Long.valueOf(jedis.hget("hello1","f1")));
//            for (int i = 0; i < 10; i++) {
//                jedis.zadd("hello", System.currentTimeMillis(), "member_"+i);
//                Thread.sleep(100);
//            }
            
//            System.out.println(jedis.zrangeByScore("hello", 1418791485685d, 1418791486315d));
            System.out.println(jedis.setbit("hello", 2511230, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
