package com.heelenyc.research.misc.copyproperty;

import org.springframework.beans.BeanUtils;

/**
 * @author yicheng
 * @since 2013年11月26日
 *
 */

class Source {
    private String m1;
    private String m2;
    
    public String getM1() {
        return m1;
    }
    public void setM1(String m1) {
        this.m1 = m1;
    }
    public String getM2() {
        return m2;
    }
    public void setM2(String m2) {
        this.m2 = m2;
    }
    
    public String getM3() {
        return m1 + m2;
    }
}

class Target{
    private String m1;
    private String m3;
    public String getM1() {
        return m1;
    }
    public void setM1(String m1) {
        this.m1 = m1;
    }
    public String getM3() {
        return m3;
    }
    public void setM3(String m3) {
        this.m3 = m3;
    }
    
    @Override
    public String toString() {
        return "m1 = " + m1 + "  m3 = "+m3;
    }
}

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Source source = new Source();
		source.setM1(null);source.setM2("m2");
		Target target = new Target();
	    BeanUtils.copyProperties(source, target);
	    System.out.println(target);
	}

}
