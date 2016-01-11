package com.heelenyc.research.misc.concurrent.condition;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yicheng
 * @since 2015年4月9日
 * 
 */
public class ConditionBan {

    private static final int full_size = 10;
    private static Queue<Double> queue = new ConcurrentLinkedQueue<Double>();

    private static Lock lock = new ReentrantLock();
    private static Condition notfull = lock.newCondition();
    private static Condition notempty = lock.newCondition();

    /**
     * @param args
     */
    public static void main(String[] args) {
        Consumer consumer1 = new Consumer("c1");
        Consumer consumer2 = new Consumer("c2");
        Consumer consumer3 = new Consumer("c3");
        consumer1.start();
        consumer2.start();
        consumer3.start();
        Producer producer1 = new Producer("p1");
        Producer producer2 = new Producer("p2");
        Producer producer3 = new Producer("p3");
        producer1.start();
        producer2.start();
        producer3.start();
    }

    public static class Consumer extends Thread {

        /**
         * @param string
         */
        public Consumer(String name) {
            setName(name);
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                System.out.println( Thread.currentThread().getName() + " get the lock");
                try {
                    while (queue.size() == 0) { // 被唤醒之后仍然要做判断
                        System.out.println("queue.size() == 0, so " + Thread.currentThread().getName() + " do notempty.await();");
                        notempty.await(); // 挂起 等待数据
                        System.err.println(Thread.currentThread().getName() + " wake up! ");
                    }
                    System.out.println(Thread.currentThread().getName() + " consume : " + queue.poll() + " ; queue size : " + queue.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    notfull.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println( Thread.currentThread().getName() + " release the lock");
                }

            }
        }
    }

    public static class Producer extends Thread {
        /**
         * @param string
         */
        public Producer(String name) {
            setName(name);
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                System.out.println( Thread.currentThread().getName() + " get the lock");
                try {
                    while (queue.size() >= full_size) { // 被唤醒时再次判断
                        System.out.println("queue.size() >= full_size, so " + Thread.currentThread().getName() + " do notfull.await();");
                        notfull.await(); // 等待队列不满
                        System.err.println(Thread.currentThread().getName() + " wake up! ");
                    }
                    double Item = Math.random();
                    queue.add(Item);
                    System.out.println(Thread.currentThread().getName() + " produce : " + Item + " ; queue size : " + queue.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    notempty.signal();// 唤醒一个读线程

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println( Thread.currentThread().getName() + " release the lock");
                }
            }
        }
    }

}
