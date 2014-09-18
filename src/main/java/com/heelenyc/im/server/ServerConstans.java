package com.heelenyc.im.server;

/**
 * @author yicheng
 * @since 2014年9月18日
 *
 */
public final class ServerConstans {

    public static final int SERVER_NET_CONF_BACKLOG = 100;
    public static final int SERVER_NET_CONG_READ_TIMEOUT = 50;
    
    
    public static final String REMOTEIP = "127.0.0.1";
    public static final int PORT = 8080;
    public static final int LOCAL_PORT = 12088;
    public static final String LOCALIP = "127.0.0.1";
    
    /**
     * 
     */
    public static int MESSAGE_MAX_FRAME_LENGTH = 1024 * 1024;
    public static int MESSAGE_LENGTH_FIELD_OFFSET = 4;
    public static int MESSAGE_LENGTH_FIELD_LENGTH = 4;
    
}
