package com.heelenyc.research.misc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.net.ssl.SSLSocket;

import org.springframework.core.task.TaskExecutor;


/**
 * @author yicheng
 * @since 2013年11月26日
 * 
 */

public class TestMain {

    private static ExecutorService es = Executors.newFixedThreadPool(1);
    /**
     * @param args
     */
    public static void main(String[] args) {
//        try {
//            Thread workThread = new Thread(new Runnable() {
//                
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000*60);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            });
//            workThread.
//            long startT = System.currentTimeMillis();
//            Future<?> task = es.submit(workThread);
//            while(System.currentTimeMillis() - startT < 10*1000){
//                Thread.sleep(1000);
//            }
//            task.cancel(true);
//                
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        SSLSocket iml;

    }
    
    
}