package com.heelenyc.research.misc.regextest;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yicheng
 * @since 2014年12月23日
 * 
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String s = "yi+cheng";
        Pattern p = Pattern.compile("^(.*?)+(.*)$");
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group(1) + m.group(2));
        }
    }

}
