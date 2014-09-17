package com.research.misc.springtest;

import java.util.HashMap;
import java.util.Map;

import com.research.common.utils.SpringBeanUtil;

/**
 * @author yicheng
 * @since 2014年2月24日
 *
 */
public class TestMain {
    
    public static void main(String args[]) {
//        TestClass s = (TestClass)SpringBeanUtil.getBean(TestClass.class);
//        s.test();
//        SpringBeanUtil.destroyContext();

        A a = (A)SpringBeanUtil.getBean(A.class);
        a.fun();
        SpringBeanUtil.destroyContext();
        
//        Map<String, String> map = new HashMap<String, String>();
//        String s1 = new String("zhangsan");
//        map.put("hellp", s1);
//        s1= new String("lisi");
//        System.out.println(map.get("hellp"));
    }
}
