package com.heelenyc.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.heelenyc.im.coder.MessageDecoder;
import com.heelenyc.im.coder.MessageEncoder;
import com.heelenyc.im.common.Constans;
import com.heelenyc.im.server.handler.ServerHeartBeatRespHandler;
import com.heelenyc.im.server.handler.ServerLoginAuthRespHandler;

/**
 * @author yicheng
 * @since 2014年9月17日
 * 
 */
public class IMServer {
    
    Log logger = LogFactory.getLog(this.getClass());

    public static void main(String[] args) throws Exception {
        new IMServer().bind();
    }

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, Constans.NET_CONF_BACKLOG).handler(new LoggingHandler(LogLevel.WARN))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws IOException {
                        ch.pipeline().addLast("MessageDecoder",
                                new MessageDecoder(Constans.MESSAGE_MAX_FRAME_LENGTH, Constans.MESSAGE_LENGTH_FIELD_OFFSET, Constans.MESSAGE_LENGTH_FIELD_LENGTH));
                        ch.pipeline().addLast("MessageEncoder", new MessageEncoder());
                        ch.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(Constans.NET_CONF_READ_TIMEOUT));
                        ch.pipeline().addLast("ServerLoginAuthHandler", new ServerLoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler", new ServerHeartBeatRespHandler());
                    }
                });

        // 绑定端口，同步等待成功
        b.bind(Constans.REMOTEIP, Constans.PORT).sync();
        // b.bind(ServerConstans.PORT).sync();
        logger.info("Netty server start ok : " + (Constans.REMOTEIP + " : " + Constans.PORT));
    }
}
