package com.research.misc.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream.PutField;
import java.io.Serializable;

/**
 * @author yicheng
 * @since 2014年6月27日
 * 
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password = "pass";
    
    public int i;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // private void writeObject(ObjectOutputStream out) {
    // try {
    // PutField putFields = out.putFields();
    // System.out.println("原密码:" + password);
    // password = "encryption";// 模拟加密
    // putFields.put("password", password);
    // System.out.println("加密后的密码" + password);
    // out.writeFields();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    //
    // private void readObject(ObjectInputStream in) {
    // try {
    // GetField readFields = in.readFields();
    // Object object = readFields.get("password", "");
    // System.out.println("要解密的字符串:" + object.toString());
    // password = "pass";// 模拟解密,需要获得本地的密钥
    // } catch (IOException e) {
    // e.printStackTrace();
    // } catch (ClassNotFoundException e) {
    // e.printStackTrace();
    // }
    //
    // }

    public static void main(String[] args) {
        try {
            /*
             * ObjectOutputStream out = new ObjectOutputStream(new
             * FileOutputStream("result.obj")); out.writeObject(new Test());
             * out.close();
             * 
             * ObjectInputStream oin = new ObjectInputStream(new
             * FileInputStream("result.obj")); Test t = (Test) oin.readObject();
             * System.out.println("解密后的字符串:" + t.getPassword()); oin.close();
             */

            /*
             * ObjectOutputStream out = new ObjectOutputStream(new
             * FileOutputStream("result.obj")); Test test = new Test(); Test
             * test2 = new Test(); // 试图将对象两次写入文件 out.writeObject(test);
             * out.flush(); System.out.println(new File("result.obj").length());
             * out.writeObject(test2); out.close(); System.out.println(new
             * File("result.obj").length());
             * 
             * ObjectInputStream oin = new ObjectInputStream(new
             * FileInputStream("result.obj")); // 从文件依次读出两个文件 Test t1 = (Test)
             * oin.readObject(); Test t2 = (Test) oin.readObject(); oin.close();
             * // ObjectInputStream oin2 = new ObjectInputStream(new
             * FileInputStream("result.obj")); // Test t2 = (Test)
             * oin2.readObject(); // oin2.close();
             * 
             * System.out.println(t1 == t2);
             */

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
            Test test = new Test();
            test.i = 1;
            out.writeObject(test);
            out.flush();
            // test.i = 2;
            // out.writeObject(test);
            // out.close();
            // ObjectInputStream oin = new ObjectInputStream(new
            // FileInputStream("result.obj"));
            // Test t1 = (Test) oin.readObject();
            // Test t2 = (Test) oin.readObject();
            // System.out.println(t1.i);
            // System.out.println(t2.i);
            // oin.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
