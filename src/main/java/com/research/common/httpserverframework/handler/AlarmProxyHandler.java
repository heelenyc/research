package com.research.common.httpserverframework.handler;

import org.apache.commons.lang.StringUtils;
import org.xlightweb.HttpResponse;
import org.xlightweb.IHttpExchange;
import org.xlightweb.IHttpRequest;

import com.research.common.httpserverframework.handler.api.AbstractHandler;

/**
 * @author yicheng
 * @since 2014年5月14日
 * 
 */
public class AlarmProxyHandler extends AbstractHandler {


    /**
     * alarm_proxy 请求 ：http://monitor.m6:001:8001/alarmproxy?action=cpuload&
     * host=monitor001&msg=Load1m 1.3275
     * <p>
     * action【必须】 host 可访问的主机名【必须】 msg【消息】
     */
    @Override
    protected void doProcess(IHttpExchange exchange) throws Exception {

        IHttpRequest request = exchange.getRequest();
        String action = request.getParameter("action");
        String msg = request.getParameter("msg");
        String host = request.getParameter("host");

        if (StringUtils.isNotEmpty(action) && StringUtils.isNotEmpty(host) && StringUtils.isNotEmpty(msg)) {
            exchange.send(new HttpResponse(400, "params error"));
        }

    }

}
