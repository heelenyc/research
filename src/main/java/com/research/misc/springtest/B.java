package com.research.misc.springtest;

import org.springframework.stereotype.Component;

/**
 * @author yicheng
 * @since 2014年9月10日
 *
 */
@Component
public class B {
    
    public B(){
        System.out.println("construct B");
    }
    
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
