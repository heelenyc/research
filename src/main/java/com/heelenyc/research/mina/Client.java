package com.heelenyc.research.mina;

/**
 * @author yicheng
 * @since 2014年12月30日
 *
 */

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Client {

    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
        /*
         * connector.getFilterChain().addLast("keepalive", new
         * KeepAliveFilter(new KeepAliveMessageFactory() {
         * 
         * @Override public boolean isResponse(IoSession session, Object
         * message) { String str = message.toString(); if (str != null) { return
         * str.equals("pong"); } return false; }
         * 
         * @Override public boolean isRequest(IoSession session, Object message)
         * { String str = message.toString(); if (str != null) { return
         * str.equals("ping"); } return false; }
         * 
         * @Override public Object getResponse(IoSession session, Object
         * request) { return "pong"; }
         * 
         * @Override public Object getRequest(IoSession session) { return null;
         * } },IdleStatus.READER_IDLE,KeepAliveRequestTimeoutHandler.LOG,60,5));
         */

        connector.setHandler(new TimeClientHander());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", BaseConfig.PORT));
        // 等待建立连接
        connectFuture.awaitUninterruptibly();
        System.out.println("连接成功");

        IoSession session = connectFuture.getSession();
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            String str = sc.next();
            if (str.equalsIgnoreCase("quit")) {
                quit = true;
            }
            session.write(str);
        }
        // 关闭
        if (session != null) {
            if (session.isConnected()) {
                session.getCloseFuture().awaitUninterruptibly();
            }
            connector.dispose(true);
        }
        sc.close();
    }
}
