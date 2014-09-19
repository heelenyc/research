package com.heelenyc.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.heelenyc.im.client.handler.HeartBeatReqHandler;
import com.heelenyc.im.client.handler.LoginAuthReqHandler;
import com.heelenyc.im.coder.NettyMessageDecoder;
import com.heelenyc.im.coder.NettyMessageEncoder;
import com.heelenyc.im.common.Constans;

/**
 * @author yicheng
 * @since 2014年9月17日
 * 
 */
public class IMClient {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) throws Exception {

        // 配置客户端NIO线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("MessageDecoder",
                            new NettyMessageDecoder(Constans.MESSAGE_MAX_FRAME_LENGTH, Constans.MESSAGE_LENGTH_FIELD_OFFSET, Constans.MESSAGE_LENGTH_FIELD_LENGTH));
                    ch.pipeline().addLast("MessageEncoder", new NettyMessageEncoder());
                    ch.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(Constans.NET_CONF_READ_TIMEOUT));
                    ch.pipeline().addLast("ClientLoginAuthHandler", new LoginAuthReqHandler());
                    ch.pipeline().addLast("HeartBeatHandler", new HeartBeatReqHandler());
                }
            });
            // 发起异步连接操作
            ChannelFuture future = b.connect(new InetSocketAddress(host, port), new InetSocketAddress(Constans.LOCAL_IP, Constans.LOCAL_PORT));
            future.sync();
            // TimeUnit.SECONDS.sleep(10);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            connect(Constans.PORT, Constans.REMOTEIP);// 发起重连操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new IMClient().connect(Constans.PORT, Constans.REMOTEIP);
    }
}