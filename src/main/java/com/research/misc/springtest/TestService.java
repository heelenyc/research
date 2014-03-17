package com.research.misc.springtest;


/**
 * @author yicheng
 * @since 2014年2月24日
 * 
 */
public class TestService {

    private String name;
    
    public TestService(String name){
        this.name = name;
    }

    public TestService() {
        System.out.println("constructor!");
    }

    public void start() throws InterruptedException {
        System.out.println("start service!");
        Thread.sleep(1000 * 20);
    }

    public void stop() {
        System.out.println("stop service!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
