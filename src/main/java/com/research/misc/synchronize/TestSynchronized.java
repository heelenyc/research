package com.research.misc.synchronize;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yicheng
 * @since 2014年1月17日
 *
 */
public class TestSynchronized {
    
    public static Map<String , Object> lockMap = new HashMap<String, Object>();

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
//        lockMap.put("key", "value");
//        TestSynchronized t = new TestSynchronized();
//        t.new outerLockThread().start();
//        Thread.sleep(1*1000);
//        t.new innerLockThread().start();
        Object obj = null;
        synchronized (obj){
            System.out.println("error");
        };
    }
    
    public class outerLockThread extends Thread{

        @Override
        public void run() {
            System.out.println("outer lock");
            synchronized (lockMap) {
                try {
                    System.out.println("get key in outter : " + lockMap.get("key"));
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("get key in outter run: " + lockMap.get("key"));
            }
        }
        
    }
    
    public class innerLockThread extends Thread{
        @Override
        public void run(){
            System.out.println("get key in inner : " + lockMap.get("key"));
            synchronized (lockMap.get("key")) {
                System.out.println("get key in inner run() synchronized : " + lockMap.get("key"));
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("inner lock release");
            }
        }
    }

}
