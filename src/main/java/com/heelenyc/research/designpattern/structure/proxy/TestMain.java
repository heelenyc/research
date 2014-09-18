package com.heelenyc.research.designpattern.structure.proxy;

import com.heelenyc.research.designpattern.structure.proxy.api.Bean;
import com.heelenyc.research.designpattern.structure.proxy.impl.BeanWrapper;

/**
 * @author yicheng
 * @since 2014年3月16日
 * 
 */
public class TestMain {

    public static void main(String[] args){
        Bean  b = new BeanWrapper();
        b.action();
    }
}
