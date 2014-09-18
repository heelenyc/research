package com.heelenyc.research.common.httpserverframework;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xlightweb.server.HttpServer;

import com.immomo.mcf.util.LogWrapper;

/**
 * 基于http协议的日志服务,实现了IHttpRequestHandler xlightweb接口
 * 
 */
public class HubbleHttpServer {
    
    private static Logger logger = LogWrapper.getLogger("hubbleHttpServer");
    private DispatchRequestHandler handle;
    
    /**
     * 内置的 xlightweb httpserver
     */
    private HttpServer server;
    
    private String host;
    private int port;
    
    /**
     * constructor
     * 
     * @param host host
     * @param port port
     */
    public HubbleHttpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * constructor
     * 
     * @param port port
     */
    public HubbleHttpServer(int port) {
        this(null, port);
    }

    /**
     * start
     */
    public void start() {
        try {
            if (StringUtils.isBlank(host)) {
                server = new HttpServer(port, handle);
            } else {
                server = new HttpServer(host, port, handle);
            }
            server.start();
            logger.info("Http taskserver startup on " + (host == null ? "0.0.0.0" : host) + ":" + port);
        } catch (Exception e) {
            logger.error("Http taskserver startup failed.", e);
        }
    }

    /**
     * stop
     */
    public void stop() {
        server.close();
        logger.info("Http logserver stoped.");
    }

	public void setHandle(DispatchRequestHandler handle) {
		this.handle = handle;
	}
}
