package com.research.misc.mongodb;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * @author yicheng
 * @since 2014年1月26日
 *
 */
public class GroupTest {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        MongoClient mongoClient = new MongoClient("localhost:28018");
        DBCollection coll = mongoClient.getDB("hubble").getCollection("monitorUnit");
        
        BasicDBObject key = new BasicDBObject("monitorKey", true);
        DBObject query = new BasicDBObject("monitorType","redis");
        BasicDBObject initial = new BasicDBObject();
        initial.put("count",0);
        initial.put("alarmcount", 0);
        String reduce = "function(obj,out){out.count++; out.alarmcount = out.alarmcount + obj.alarmCount}";
        String finalize = "function(out) { out.outer =   out.alarmcount / out.count ; }";
        BasicDBList returnList = (BasicDBList)coll.group(key, query, initial, reduce,finalize);
        System.out.println(returnList);
        for (Object item : returnList) {
            System.out.println(((DBObject) item).get("monitorKey"));
        }
    }

}
