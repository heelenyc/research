package com.research.misc.springtest;

import com.research.common.utils.SpringBeanUtil;

/**
 * @author yicheng
 * @since 2014年2月24日
 *
 */
public class TestMain {
    
    public static void main(String args[]) {
        TestService s = (TestService)SpringBeanUtil.getBean(TestService.class);
        System.out.println(s.getName());
        SpringBeanUtil.destroyContext();
        
    }
}
