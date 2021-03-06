package com.heelenyc.research.misc.testException;

import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yicheng
 * @since 2014年2月19日
 * 
 */
public class TestMain {

    // 通过适配器静态绑定
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);
    
    // 通过 class loader 查找具体的日志库
    private static Log logger1 = LogFactory.getLog(TestMain.class);
 

    static{
//        PropertyConfigurator.configure("/Users/yicheng/Documents/workspace/Test/src/conf/log4j.properties");
//        PropertyConfigurator.configure("log4j.properties");
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
//            ClassLoader loader = TestMain.class.getClassLoader();
//            System.out.println(System.getenv());
            new TestMain().test();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass().getSimpleName());
//            logger.error("logger:", e);
//            logger1.error("logger:", e);
        }
        ;
    }

    private void test() throws Exception {
        Exception ex = new TimeoutException();
        ex.initCause(new Exception("i am int cause"));
        ex.addSuppressed(new Exception("i am suppered 1"));
        ex.addSuppressed(new Exception("i am suppered 2"));
        throw ex;
    }
    
    private void div() {
        System.out.println(2/0);
    }
}
