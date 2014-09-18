package com.heelenyc.research.misc;

import javassist.expr.NewArray;

import com.immomo.mcf.util.Pair;

/**
 * @author yicheng
 * @since 2014年2月25日
 * 
 */
public class TestTryblock {

    // private final double value;

    // /**
    // *
    // */
    // public TestTryblock(double value) {
    // this.value = value;
    // }

    // public void fun(double value) {
    // this.value = value;
    // }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println(number("||1d".split("\\|")[1]));
        // new Pair<Double, Double>(null,12312.33d);
        // System.out.println(new Double(0.1));
        // for (double i = 0; i < 100; i += 0.1d) {
        // System.out.println(i);
        // }

        // System.out.println(Double.compare(new Double(1.1d) , new
        // Double(1.1d)));
        System.out.println(Double.MAX_EXPONENT);
        System.out.println(Long.toHexString(Double.doubleToLongBits(new Double(Double.MAX_VALUE))));
    }

    static int test() {
        int x = 1;
        try {
            return x;
        } finally {
            x++;
        }
    }

}
