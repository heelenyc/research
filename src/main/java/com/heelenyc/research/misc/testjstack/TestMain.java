package com.heelenyc.research.misc.testjstack;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author yicheng
 * @since 2014年8月7日
 * 
 */
public class TestMain {

    private static volatile Object mutex = new Object();
    private static volatile Object mutex_1 = new Object();
    private static volatile Object mutex_2 = new Object();
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    /**
     * @param args
     */
    public static void main(String[] args) {
//        new ProductThread("product").start();
//        for (int i = 0; i < 10; i++) {
//            // new TestThread("heelenyc"+i).start();
//            new ConsumThread("consum" + i).start();
//        }
        new DeadLockThread1("dead1").start();
        new DeadLockThread2("dead2").start();
    }
    
    private static class DeadLockThread1 extends Thread {

        public DeadLockThread1(String string) {
            super(string);
        }

        @Override
        public void run() {
            synchronized (mutex_1) {
                try {
                    sleep(1000);
                    synchronized (mutex_2) {
                        System.out.println(this.getName() + "get all");
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        }

    }
    
    private static class DeadLockThread2 extends Thread {

        public DeadLockThread2(String string) {
            super(string);
        }

        @Override
        public void run() {
            synchronized (mutex_2) {
                try {
                    sleep(1000);
                    synchronized (mutex_1) {
                        System.out.println(this.getName() + "get all");
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        }

    }

    private static class ProductThread extends Thread {

        public ProductThread(String string) {
            super(string);
        }

        @Override
        public void run() {
            int count = 0;
            while (true) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        for (int i = 0; i < 100; i++) {
                            queue.add(count);
                            count++;
                        }
                        queue.notifyAll();
                    } else {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    private static class ConsumThread extends Thread {
        public ConsumThread(String string) {
            super(string);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    if (queue.size() > 0) {
                        System.out.println(this.getName() + " poll " + queue.poll());
                    } else {
                        queue.notifyAll();
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static class TestThread extends Thread {

        /**
         * @param string
         */
        public TestThread(String string) {
            super(string);
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                synchronized (mutex) {
                    System.out.println(i);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
