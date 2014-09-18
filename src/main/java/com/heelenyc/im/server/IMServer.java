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

import com.heelenyc.im.coder.NettyMessageDecoder;
import com.heelenyc.im.coder.NettyMessageEncoder;
import com.heelenyc.im.server.handler.HeartBeatRespHandler;
import com.heelenyc.im.server.handler.LoginAuthRespHandler;


/**
 * @author yicheng
 * @since 2014年9月17日
 * 
 */
public class IMServer {

    public static void main(String[] args) throws Exception {
        new IMServer().bind();
    }

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, ServerConstans.SERVER_NET_CONF_BACKLOG).handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws IOException {
                        ch.pipeline().addLast("MessageDecoder",new NettyMessageDecoder(ServerConstans.MESSAGE_MAX_FRAME_LENGTH, ServerConstans.MESSAGE_LENGTH_FIELD_OFFSET, ServerConstans.MESSAGE_LENGTH_FIELD_LENGTH));
                        ch.pipeline().addLast("MessageEncoder",new NettyMessageEncoder());
                        ch.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(ServerConstans.SERVER_NET_CONG_READ_TIMEOUT));
                        ch.pipeline().addLast("ServerLoginAuthHandler",new LoginAuthRespHandler());
                        ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                    }
                });

        // 绑定端口，同步等待成功
        b.bind(ServerConstans.REMOTEIP, ServerConstans.PORT).sync();
        System.out.println("Netty server start ok : " + (ServerConstans.REMOTEIP + " : " + ServerConstans.PORT));
    }
}
