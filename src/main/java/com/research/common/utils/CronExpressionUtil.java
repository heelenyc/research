package com.research.common.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;

public class CronExpressionUtil {
	
	private static Log logger = LogFactory.getLog(CronExpressionUtil.class);

	/**
     * 检查给定时间是否满足指定的crontab  (eg: 星/2 9-12 星 星 ?;星/3 13-18 星 星 ?)
     * @param time
     * @param crontablist
     * @return 如果有 则返回对应的CronExpression 对象，否则返回空
     * @throws ParseException 
     */
    public static CronExpression getSatisfiedCronExpression(Date time,String crontablist) throws ParseException{
    	
    	if (time == null || crontablist == null)	return null;
    	
    	String[] cronlist =  crontablist.split(";|\\|");
    	CronExpression cronExpression = null;
    	
    	for(String cron : cronlist){
    		if (cron == null || cron.trim() == "") {
				continue;
			}
    		cron = "0 " + cron;
    		cronExpression = new CronExpression(cron);
    		if(cronExpression.isSatisfiedBy(time)){
    			logger.debug("Satisfied crontab :" + cron);
    			return cronExpression;
    		}
    			
    	}
    	//否则或者默认为不命中
    	return null;
    }
    
    /**
     * 给定的串是否是合法的crontab表达式,以分号隔开的
     * @param crontab
     * @return
     * @throws ParseException
     */
    public static boolean isValidContablist(String cronlist) {
		String[] cronarray = cronlist.split(";|:");
    	for (String cron : cronarray) {
			if(CronExpressionUtil.isValidExpression("0 " + cron) == false)
				return false;
		}
    	return true;
	}
    
    /**
     * 给定的串是否是合法的crontab表达式
     * @param crontab
     * @return
     * @throws ParseException
     */
    public static boolean isValidExpression(String crontab) {
		
    	return CronExpression.isValidExpression(crontab);
    }
    
    public static void main(String[] args) {
        System.out.println(isValidContablist("0 12 ? * 2;0 12 ? * 2"));
    }
}
