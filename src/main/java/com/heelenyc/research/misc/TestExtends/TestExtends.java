package com.heelenyc.research.misc.TestExtends;

/**
 * @author yicheng
 * @since 2014年3月5日
 *
 */
public class TestExtends {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Supper s = new Subber();
        s.setName("hello world!");
        System.out.println(s.getName());
    }

}

class Supper{
    protected String name = "supper";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Subber extends Supper{
    private String name = "subber";

    public String getName() {
        return name;
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}