package com.research.misc.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ChildChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * @author yicheng
 * @since 2013年12月23日
 * 
 */
public class HelloWorldServer {

    /**
     * @param args
     */
    public static void main(String args[]) {
        // Server服务启动器
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        // 设置一个处理客户端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new HelloServerHandler());
            }
        });
        // 开放8000端口供客户端访问。
        bootstrap.bind(new InetSocketAddress(8000));
    }

    private static class HelloServerHandler extends SimpleChannelHandler {

        /**
         * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."
         * 
         * @alia OneCoder
         * @author lihzh
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            System.out.println("Hello world, I'm server.");
        }

        @Override
        public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.handleUpstream(ctx, e);
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            // TODO Auto-generated method stub
            // super.messageReceived(ctx, e);
            
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.exceptionCaught(ctx, e);
        }

        @Override
        public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.channelOpen(ctx, e);
        }

        @Override
        public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.channelBound(ctx, e);
        }

        @Override
        public void channelInterestChanged(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.channelInterestChanged(ctx, e);
        }

        @Override
        public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.channelDisconnected(ctx, e);
        }

        @Override
        public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.channelUnbound(ctx, e);
        }

        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.channelClosed(ctx, e);
        }

        @Override
        public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.writeComplete(ctx, e);
        }

        @Override
        public void childChannelOpen(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.childChannelOpen(ctx, e);
        }

        @Override
        public void childChannelClosed(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.childChannelClosed(ctx, e);
        }

        @Override
        public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.handleDownstream(ctx, e);
        }

        @Override
        public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.writeRequested(ctx, e);
        }

        @Override
        public void bindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.bindRequested(ctx, e);
        }

        @Override
        public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.connectRequested(ctx, e);
        }

        @Override
        public void setInterestOpsRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.setInterestOpsRequested(ctx, e);
        }

        @Override
        public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.disconnectRequested(ctx, e);
        }

        @Override
        public void unbindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.unbindRequested(ctx, e);
        }

        @Override
        public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            // TODO Auto-generated method stub
            super.closeRequested(ctx, e);
        }
    }

}
