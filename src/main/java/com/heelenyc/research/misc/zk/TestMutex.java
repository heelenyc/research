package com.heelenyc.research.misc.zk;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TestMutex {

	private static CuratorFramework client;

	public static void main(String[] args) {
		
        int sessionTimeoutMs = 3000;
        int connectionTimeoutMs = 3000;
        int baseSleepTimeMs = 1000;
        int maxRetries = 3 ;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        client = CuratorFrameworkFactory.newClient("localhost:2181", sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
        client.start();
		
        
		
		
	}
	
	class LockThread extends Thread {
		
		
		
		@Override
		public void run() {
			InterProcessMutex mutex = new InterProcessMutex(client, "/mutex-lock");
			try {
				if (mutex.acquire(3, TimeUnit.SECONDS)) {
					System.out.println(this.getName() + " get lock!");
					
					Thread.sleep(30000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					mutex.release();
					System.out.println(this.getName() + " release lock!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
