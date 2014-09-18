package com.heelenyc.research.misc.task;

import java.util.TimerTask;

/**
 * @author yicheng
 * @since 2013年12月6日
 *
 */
public class MyTask extends TimerTask {

    @Override
    public void run() {
        StaticTools tools = new StaticTools();
        tools.setTools("");
        System.out.println("hello world!");
        
        NonStaticTools nontools = new NonStaticTools();
        
    }
    
    public static  class StaticTools{
        private String tools;

        public String getTools() {
            return tools;
        }

        public void setTools(String tools) {
            this.tools = tools;
        }
        
        
    }
    
    public class NonStaticTools{
        private static final String tools = "sdfs";

        public String getTools() {
            return tools;
        }

    }

}
