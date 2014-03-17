package com.edu.heelenyc.task;

import java.util.Timer;
import java.util.TimerTask;

import com.edu.heelenyc.task.MyTask.NonStaticTools;
import com.edu.heelenyc.task.MyTask.StaticTools;

/**
 * @author yicheng
 * @since 2013年12月6日
 * 
 */
public class TestMain {

    public static void main(String[] args) {

        MyTask mt = new MyTask();
        
        StaticTools tools = new StaticTools();
        tools.setTools("");
        NonStaticTools nontools = mt.new NonStaticTools();
        nontools.getTools();
    }

}
