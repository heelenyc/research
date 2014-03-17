package com.edu.heelenyc.springtest;

import com.edu.common.utils.SpringBeanUtil;

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
