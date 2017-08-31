package com.heelenyc.research.reflect.buiss;

/**
 * @author yicheng
 * @since 2014年10月13日
 *
 */
public class AddImp implements AddInface {

    //private AddImp(){};
    @Override
    public int add(int o1, int o2) {
        return o1 + o2 ;
    }

    @Override
    public long addlong(long o1, long o2) {
        // TODO Auto-generated method stub
        return o1 + o2;
    }
}
