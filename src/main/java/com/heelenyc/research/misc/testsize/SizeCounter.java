package com.heelenyc.research.misc.testsize;

import java.lang.instrument.Instrumentation;

/**
 * @author yicheng
 * @since 2015年7月21日
 * 
 */
public class SizeCounter {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
