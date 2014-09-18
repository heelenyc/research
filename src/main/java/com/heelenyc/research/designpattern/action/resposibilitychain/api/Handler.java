package com.heelenyc.research.designpattern.action.resposibilitychain.api;

/**
 * @author yicheng
 * @since 2014年3月18日
 *
 */
public interface Handler {

    boolean process(Request req);
    
    void setSuccessor(Handler handler);
}
