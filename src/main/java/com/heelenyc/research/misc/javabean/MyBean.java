package com.heelenyc.research.misc.javabean;

/**
 * @author yicheng
 * @since 2014年6月25日
 *
 */
public class MyBean {

    private String id;
    
    private String userName;
    
    private String cName;
    
    private String URL;
    
    private String Host;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }
    
    public static void main(String[] args){
        MyBean bean = new MyBean();
        
        //bean.
    }
}
