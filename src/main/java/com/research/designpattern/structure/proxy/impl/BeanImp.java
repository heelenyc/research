package com.research.designpattern.structure.proxy.impl;

import com.research.designpattern.structure.proxy.api.Bean;

/**
 * @author yicheng
 * @since 2014年3月16日
 *
 */
public class BeanImp implements Bean {

    @Override
    public void action(){
        System.out.println("Go Go bean!");
    }
}
