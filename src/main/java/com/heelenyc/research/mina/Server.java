package com.heelenyc.research.mina;

/**
 * @author yicheng
 * @since 2014年12月30日
 *
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class Server {

    private static final int PORT = BaseConfig.PORT;

    public static void main(String[] args) throws IOException {

        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
        acceptor.getFilterChain().addLast("keepalive", new KeepAliveFilter(new KeepAliveMessageFactory() {
            
            @Override
            public boolean isResponse(IoSession session, Object message) {
                String str = message.toString();
                if (str != null) {
                    return str.equals("pong");
                }
                return false;
            }
            
            @Override
            public boolean isRequest(IoSession session, Object message) {
                String str = message.toString();
                if (str != null) {
                    return str.equals("ping");
                }
                return false;
            }
            
            @Override
            public Object getResponse(IoSession session, Object request) {
                return "pong";
            }
            
            @Override
            public Object getRequest(IoSession session) {
                return "ping";
            }
        },IdleStatus.BOTH_IDLE,KeepAliveRequestTimeoutHandler.LOG,10,10));
        
        acceptor.setHandler(new TimeServerHandler());

        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);

        acceptor.bind(new InetSocketAddress(PORT));

    }

}
