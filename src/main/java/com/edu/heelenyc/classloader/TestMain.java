package com.edu.heelenyc.classloader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author yicheng
 * @since 2014年3月4日
 * 
 */
public class TestMain {

    /**
     * @param args
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String name = "com.edu.heelenyc.classloader.TestBlank";
        URL url = new URL("TestBlank.class");
        ClassLoader cl = new MyClassLoader(name, url);
        Class<?> c = Class.forName(name, false, cl);
        // 实例化
        Object obj = c.newInstance();
        ApiBlank i = (ApiBlank) obj;
        i.run();
    }

}
