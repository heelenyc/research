package com.heelenyc.research.misc.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author yicheng
 * @since 2014年3月4日
 * 
 */

public class MyClassLoader extends ClassLoader {
    private static MyClassLoader cl = null;
    private static boolean flag = true;
    private InputStream classFile = null;
    private String name = null;

    /**
     * @param name
     *            String 类全名
     * @param url
     *            URL 类路径
     * @throws FileNotFoundException
     * @throws IOException
     */
    public MyClassLoader(String name, URL url) throws FileNotFoundException, IOException {
        super(getSystemClassLoader());
        this.name = name + ".class";
        // 打开URL指定的资源
        URLConnection con = url.openConnection();
        InputStream classIs = con.getInputStream();
        this.classFile = classIs;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        // 读取文件流
        for (int i = 0; (i = classIs.read(buf)) != -1;) {
            baos.write(buf, 0, i);
        }
        classIs.close();
        baos.close();

        // 创建新的类对象
        byte[] data = baos.toByteArray();
        defineClass(name, data, 0, data.length);
    }

    /**
     * 重载 getResourceAsStream() 是为了返回该类的文件流。
     * 
     * @return an InputStream of the class bytes, or null
     */
    public InputStream getResourceAsStream(String resourceName) {
        try {
            if (resourceName.equals(name)) {
                return this.classFile;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}