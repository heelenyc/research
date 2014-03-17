package com.research.designpattern.structure.proxy.impl;

import com.research.designpattern.structure.proxy.api.Bean;


/**
 * @author yicheng
 * @since 2014年3月16日
 *
 */
public class BeanWrapper implements Bean {

    private Bean bean;

    /**
     * 
     */
    public BeanWrapper() {
        bean = new BeanImp();
    }
    @Override
    public void action() {
        System.out.println("Start!");
        bean.action();
        System.out.println("Over!");
    }
    
    
}
