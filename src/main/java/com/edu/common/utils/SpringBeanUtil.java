package com.edu.common.utils;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring bean util.
 * 
 */
public final class SpringBeanUtil {
    /**
     * Constructor
     */
    private SpringBeanUtil() {
    }

    private static AbstractApplicationContext  context;

    private static AbstractApplicationContext getApplicationContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext(new String[]{"conf/applicationContext.xml","conf/applicationContext-quartz.xml"});
        }
        return context;
    }

    /**
     * 根据提供的bean名称得到相应的服务类
     * 
     * @param name bean名称
     * @return bean.
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据提供的bean class得到相应的服务类
     * 
     * @param <T> class type.
     * @param clazz class.
     * @return bean.
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    
    /**
     * 手动销毁application context
     */
    public static void destroyContext() {
		
    	if (context != null) {
			context.close();
		}
	}

}
