package com.research.misc.springtest;

import javax.annotation.Resource;

/**
 * @author yicheng
 * @since 2014年9月10日
 *
 */
public abstract class TestSubClass  {
    private TestService testService;
    
    public void testsub(){
        System.out.println(testService.getName());
    }
}
