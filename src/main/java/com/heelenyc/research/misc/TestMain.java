package com.heelenyc.research.misc;

import java.util.concurrent.TimeUnit;

/**
 * @author yicheng
 * @since 2013年11月26日
 * 
 */

public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        MyThread t = new MyThread();
//        t.setDaemon(true);
//        t.start();
        BadBean b = new BadBean(1);
        
        System.out.println(b.getJ());
    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("MyThread begin!");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThread over!");

    }
}

class BadBean {
    public static int i = 0;

    private int j;
    
    /**
     * 
     */
    public BadBean(int j) {
        this.j = j;
    }
    
    static {
            i = 1 / 0;
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        BadBean.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    
}