package com.heelenyc.research.misc.task;

import java.util.Timer;
import java.util.TimerTask;

import com.heelenyc.research.misc.task.MyTask.NonStaticTools;
import com.heelenyc.research.misc.task.MyTask.StaticTools;

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
