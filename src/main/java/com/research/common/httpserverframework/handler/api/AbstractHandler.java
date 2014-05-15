package com.research.common.httpserverframework.handler.api;

import org.apache.log4j.Logger;
import org.xlightweb.HttpResponse;
import org.xlightweb.IHttpExchange;
import org.xlightweb.IHttpRequest;

import com.immomo.mcf.util.LogWrapper;

/**
 * @author yicheng
 * @since 2014年5月14日
 * 
 */
public abstract class AbstractHandler {

    private Logger logger = LogWrapper.getLogger("abstractHandler");
    /**
     * 处理的关键字
     */
    protected String uriKey;


    public void process(IHttpExchange exchange) {
        //if (exchange.getRequest().getRequestURI().equals(uriKey)) {
        IHttpRequest request = exchange.getRequest();
        try {
            doProcess(exchange);
        } catch (Exception e) {
            logger.error("error @ request : " + request.getRequestUrl(), e);
            // 返回 500 错误
            try {
                exchange.send(new HttpResponse(500, "Server Error"));
            } catch (Exception e1) {
                logger.error("error @ request : " + request.getRequestUrl(), e1);
            }
        }
        //}
    }

    abstract protected void doProcess(IHttpExchange exchange) throws Exception;

    public void setUriKey(String uriKey) {
        this.uriKey = uriKey;
    }

    public String getUriKey() {
        return uriKey;
    }

}