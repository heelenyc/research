package com.heelenyc.research.misc.threadlocal;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yicheng
 * @since 2015年2月3日
 * 
 */
public class TestMain {

    private static ExecutorService es = Executors.newFixedThreadPool(5);

    /**
     * @param args
     */
    public static void main(String[] args) {
        RaceTunnle rt = new RaceTunnle(0);
        System.out.println(rt.position.get());

        for (int i = 1; i <= 10; i++) {
            final int j = i;
            es.execute(new Runnable() {
                @Override
                public void run() {
                    RaceTunnle rt = new RaceTunnle(j);
                    System.out.println(rt.position.get());
                }
            });
        }

        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            String str = sc.next();
            if (str.equalsIgnoreCase("quit")) {
                quit = true;
            } else {
                System.out.println(str);
            }
        }
        sc.close();
    }

}

class RaceTunnle {
    private int index;

    /**
     * @param i
     */
    public RaceTunnle(int i) {
        this.setIndex(i);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    ThreadLocal<Integer> position = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            System.out.println(Thread.currentThread().getName() + " initialValue");
            return index;
        }
    };

}