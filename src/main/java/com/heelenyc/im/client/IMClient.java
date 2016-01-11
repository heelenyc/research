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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.heelenyc.im.client.handler.ClientHeartBeatReqHandler;
import com.heelenyc.im.client.handler.ClientLoginAuthReqHandler;
import com.heelenyc.im.coder.MessageDecoder;
import com.heelenyc.im.coder.MessageEncoder;
import com.heelenyc.im.common.Constans;

/**
 * @author yicheng
 * @since 2014年9月17日
 * 
 */
public class IMClient {

    private Log logger = LogFactory.getLog(this.getClass());

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(final int port, final String host) throws Exception {

        // 配置客户端NIO线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("MessageDecoder", new MessageDecoder(Constans.MESSAGE_MAX_FRAME_LENGTH, Constans.MESSAGE_LENGTH_FIELD_OFFSET, Constans.MESSAGE_LENGTH_FIELD_LENGTH));
                    ch.pipeline().addLast("MessageEncoder", new MessageEncoder());
                    ch.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(Constans.NET_CONF_READ_TIMEOUT));
                    ch.pipeline().addLast("ClientLoginAuthHandler", new ClientLoginAuthReqHandler());
                    // ch.pipeline().addLast("HeartBeatHandler", new
                    // ClientHeartBeatReqHandler());
                }
            });
            // 发起异步连接操作
            ChannelFuture future = b.connect(new InetSocketAddress(host, port), new InetSocketAddress(Constans.LOCAL_IP, Constans.LOCAL_PORT));
            future.sync();
            // TimeUnit.SECONDS.sleep(10);
            future.channel().closeFuture().sync();
            logger.info("client closed!");
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            // 所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            connect(port, host);// 发起重连操作
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