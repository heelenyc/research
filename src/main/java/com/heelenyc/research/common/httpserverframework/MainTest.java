package com.heelenyc.research.common.httpserverframework;

import com.heelenyc.research.common.utils.SpringBeanUtil;


/**
 * @author yicheng
 * @since 2014年5月14日
 *
 */
public class MainTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Object Server = SpringBeanUtil.getBean(HubbleHttpServer.class);
    }

}
