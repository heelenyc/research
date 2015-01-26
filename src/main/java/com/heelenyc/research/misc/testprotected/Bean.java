package com.heelenyc.research.misc.testprotected;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yicheng
 * @since 2015年1月15日
 * 
 */
public class Bean {

    private Integer i;
    Integer j = new Integer(11);
    
    Map<String,String> map = new HashMap<>();

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

}
