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

import com.heelenyc.im.coder.NettyMessageDecoder;
import com.heelenyc.im.coder.NettyMessageEncoder;
import com.heelenyc.im.server.handler.HeartBeatRespHandler;
import com.heelenyc.im.server.handler.LoginAuthRespHandler;
import com.heelenyc.research.netty.protocol.netty.server.NettyServer;

//import com.research.netty.protocol.netty.NettyConstant;
//import com.research.netty.protocol.netty.codec.NettyMessageDecoder;
//import com.research.netty.protocol.netty.codec.NettyMessageEncoder;
//import com.research.netty.protocol.netty.server.HeartBeatRespHandler;
//import com.research.netty.protocol.netty.server.LoginAuthRespHandler;

/**
 * @author yicheng
 * @since 2014年9月17日
 * 
 */
public class IMServer {

    private Log logger = LogFactory.getLog(this.getClass());

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, ServerConstans.SERVER_BACKLOG).handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws IOException {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(ServerConstans.READTIMEOUT));
                        ch.pipeline().addLast(new LoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                    }
                });

        // 绑定端口，同步等待成功
        b.bind(ServerConstans.REMOTEIP, ServerConstans.PORT).sync();
        logger.info("Netty server start ok : " + (ServerConstans.REMOTEIP + " : " + ServerConstans.PORT));
    }
}
