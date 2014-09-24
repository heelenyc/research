package com.heelenyc.im.server.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.heelenyc.im.common.entity.Header;
import com.heelenyc.im.common.entity.Message;
import com.heelenyc.im.common.entity.MessageType;

public class ServerHeartBeatRespHandler extends ChannelHandlerAdapter {
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        // 返回心跳应答消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            logger.info("Receive client heart beat message : ---> " + message);
            Message heartBeat = buildHeatBeat();
            logger.info("Send heart beat response message to client : ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else
            ctx.fireChannelRead(msg);
    }

    private Message buildHeatBeat() {
        Message message = new Message();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }

}
