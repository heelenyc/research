package com.heelenyc.research.reflect;

/**
 * @author yicheng
 * @since 2014年10月13日
 *
 */
public class AddImp implements AddInface {

    @Override
    public int add(int o1, int o2) {
        return o1 + o2 ;
    }
}
