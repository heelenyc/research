package com.heelenyc.soar;

import com.heelenyc.soar.app.AlgorithmApi;
import com.heelenyc.soar.consumer.service.ConsumerServiceFactory;
import com.heelenyc.soar.consumer.service.PropertyBasedConsumerServiceFactory;
import com.heelenyc.soar.core.exception.InitializationException;

/**
 * @author yicheng
 * @since 2014年10月16日
 *
 */
public class ConsumerTestMain {
    
    public static void main(String[] args){
        
        try {
            ConsumerServiceFactory sf = new PropertyBasedConsumerServiceFactory("soatest.properties");
            
            AlgorithmApi api = sf.getInstance("/service/algorithm");
            
            System.out.println(api.add(1, 2));
        
        } catch (InitializationException e) {
            e.printStackTrace();
        }
        
        
    }
}
