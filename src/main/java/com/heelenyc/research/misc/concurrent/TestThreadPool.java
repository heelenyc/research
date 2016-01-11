package com.heelenyc.research.misc.concurrent;

import java.security.spec.ECField;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yicheng
 * @since 2014年4月2日
 * 
 */
class worker implements Callable<Boolean> {

    private Integer id;

    /**
     * 
     */
    public worker(int i) {
        this.id = i;
    }

    @Override
    public Boolean call() throws Exception {
        System.out.println(String.format("i am %d ", this.id));
        return null;
    }

}

public class TestThreadPool {

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(100);

        // for (int i = 0; i < 500; i++) {
        // System.out.println("start work "+ i);
        // exec.submit(new worker(i));
        // //Thread.sleep(100);
        // }

        Future<Boolean> task = exec.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                try {

                    for(int i=0;i<Integer.MAX_VALUE;i++){
                        if (i % 10000 ==0) {
                            System.out.println(i);
                        }
                    }
                    System.out.println("finish in call!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        try {
            task.get(100, TimeUnit.MILLISECONDS);
            System.out.println("get over!");
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            task.cancel(true);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        exec.shutdown();
    }

}
