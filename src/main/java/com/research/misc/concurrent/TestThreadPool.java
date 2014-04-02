package com.research.misc.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yicheng
 * @since 2014年4月2日
 *
 */
class worker implements Callable<Boolean>{

    private Integer id;
    /**
     * 
     */
    public worker(int i) {
        this.id = i;
    }
    @Override
    public Boolean call() throws Exception {
        System.out.println(String.format("i am %d ",this.id));
        return null;
    }
    
}
public class TestThreadPool {

    /**
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(100);
        
        for (int i = 0; i < 500; i++) {
            System.out.println("start work "+ i);
            exec.submit(new worker(i));
            //Thread.sleep(100);
        }
    }

}
