package com.research.common.httpserverframework.handler;

import java.io.IOException;
import java.util.Date;

import org.xlightweb.HttpResponse;
import org.xlightweb.IHttpExchange;
import org.xlightweb.IHttpRequest;

import com.immomo.mcf.util.StringUtils;
import com.research.common.httpserverframework.handler.api.AbstractHandler;

/**
 * @author yicheng
 * @since 2014年5月14日
 * 
 */
public class TaskHandler extends AbstractHandler {

    /**
     * task请求：http://monitor.m6:001:8001/task?action=myaction&source=10.183.0
     * .111& host=monitor001&runstatus=1&message=mymessage&timestamp=13823421312
     * 
     * <p>
     * action【必须】 source 【必须】 host 可访问的主机名【可选】 runstatus 【必须 1 启动 2运行中 3结束】
     * timestamp 【必须】 message 【消息】
     * 
     * @throws IOException
     * @throws IllegalStateException
     */
    @Override
    protected void doProcess(IHttpExchange exchange) throws Exception {

        IHttpRequest request = exchange.getRequest();
        String action = request.getParameter("action");
        String source = request.getParameter("source");
        String host = request.getParameter("host");
        if (StringUtils.isEmpty(host)) {
            host = source;
        }
        Integer status = Integer.valueOf(request.getParameter("runstatus"));
        long timestamp = 0;
        if (StringUtils.isNotEmpty(request.getParameter("timestamp"))) {
            timestamp = Long.valueOf(request.getParameter("timestamp"));
        } else {
            timestamp = new Date().getTime();
        }
        exchange.send(new HttpResponse(200, "OK"));


    }

}
