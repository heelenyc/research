package com.edu.heelenyc.springdata;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.bson.NewBSONDecoder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;

/**
 * @author yicheng
 * @since 2013年11月21日
 *
 */
public class TestMain {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 * @throws IntrospectionException 
	 */
	public static void main(String[] args) throws UnknownHostException, IntrospectionException {

		MongoClient mongoClient = new MongoClient("localhost:28018");
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "test");
		
//		Query query = new Query(Criteria.where("_id").is(new ObjectId("528ed8cb0364aeaaffaa4880")));
//		Instance obj = mongoTemplate.findOne(query, Instance.class);
		
		Instance instance = new Instance();
		instance.setHost("yicheng");
		instance.setCluster("hello");
		mongoTemplate.save(instance);
	}

}
