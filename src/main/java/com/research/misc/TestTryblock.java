package com.research.misc;

import com.immomo.mcf.util.Pair;

 
/**
 * @author yicheng
 * @since 2014年2月25日
 *
 */
public class TestTryblock {

    /**
     * @param args
     */
    public static void main(String[] args) {
       //System.out.println(number("||1d".split("\\|")[1]));
//        new Pair<Double, Double>(null,12312.33d);
        System.out.println(Double.NaN >= 0.0d);
    }
    
    static int test(){
        int x = 1;
        try{
            return x;
        }finally{
            x++;
        }
    }

}
