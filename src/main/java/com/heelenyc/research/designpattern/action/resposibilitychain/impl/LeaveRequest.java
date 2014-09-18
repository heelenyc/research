package com.heelenyc.research.designpattern.action.resposibilitychain.impl;

import com.heelenyc.research.designpattern.action.resposibilitychain.api.Request;

/**
 * @author yicheng
 * @since 2014年3月18日
 *
 */
public class LeaveRequest implements Request {

    @Override
    public String getRequestStr() {
        return "to leave!";
    }

}
