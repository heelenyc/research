package com.heelenyc.research.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * @author yicheng
 *
 */
public class MongoUtil {
	
	private static Log logger = LogFactory.getLog(MongoUtil.class);

	/**
	 * 根据对应的bean生成适合mongotemplate使用的update,null-value将被unset掉,不忽略任何key
	 * @param obj
	 * @return
	 */
	public static Update BeanToUpdateOject(Object obj) {
		return BeanToUpdateOject(obj,null);
	}
    /**
     * 根据对应的bean生成适合mongotemplate使用的update，null value将被unset掉
     * @param obj
     * @return
     */
    public static Update BeanToUpdateOject(Object obj,String[] ignorKeys) {  
  
        if(obj == null){  
            return null;  
        }          
        Update update = new Update();
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属性  
                if (!key.equals("class") && !ArrayUtils.contains(ignorKeys,key)) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj); 
                    if( value == null )
                    	update.unset(key);
                    else
                    	update.set(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
        	logger.error("bean to update object error", e);
        }  
  
        return update;  
  
    }  
    
    /**
     * 将map转换为dbobject
     * @param map
     * @return
     */
    public static DBObject convert(Map<? extends Object, ? extends Object>map){
        DBObject result = new BasicDBObject();
        result.putAll(map);
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> convert(DBObject obj){
        if (obj == null) {
            return null;
        }
        return obj.toMap();
    }

    /**
     * 从游标读取结果
     * @param cursor
     * @return
     */
    public static List<Map<String,Object>> getResult(DBCursor cursor){
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        try {
           while(cursor.hasNext()) {
               result.add(MongoUtil.convert(cursor.next()));
           }
        } finally {
           cursor.close();
        }
        return result;
    }
	public static void main(String[] args) {
	    DBObject map = new BasicDBObject();
		map.put("111", 111);
		map.put("222", "222");
		System.out.println(map);
		System.out.println(convert(map));
	}

}
