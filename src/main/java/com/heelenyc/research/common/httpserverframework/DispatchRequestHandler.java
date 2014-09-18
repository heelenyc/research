package com.heelenyc.research.common.httpserverframework;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.xlightweb.IHttpExchange;
import org.xlightweb.IHttpRequest;
import org.xlightweb.IHttpRequestHandler;

import com.heelenyc.research.common.httpserverframework.handler.api.AbstractHandler;
import com.immomo.mcf.util.LogWrapper;

/**
 * @author yicheng
 * @since 2013年12月25日
 * 
 */
public class DispatchRequestHandler implements IHttpRequestHandler {

    private Logger logger = LogWrapper.getLogger("dispatchRequestHandler");

    private List<AbstractHandler> handlerList = new LinkedList<AbstractHandler>();
    private Map<String, AbstractHandler> handlerMap = new ConcurrentHashMap<String, AbstractHandler>();
    private String defalutUri = "/task";

    public void init() {
        for (AbstractHandler handler : handlerList) {
            handlerMap.put(handler.getUriKey(), handler);
        }
    }

    @Override
    public void onRequest(IHttpExchange exchange) throws IOException {
        IHttpRequest request = exchange.getRequest();
        String uri = request.getRequestURI();
        if (handlerMap.get(uri) != null) {
            handlerMap.get(uri).process(exchange);
        } else {
            handlerMap.get(defalutUri).process(exchange);
            logger.warn("not found handler for request : " + request.getRequestUrl() + " , use taskHandler as default handler");
        }

    }

    public void setHandlerList(List<AbstractHandler> handlerList) {
        this.handlerList = handlerList;
    }

}
