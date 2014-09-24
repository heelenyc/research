/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.heelenyc.im.client.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.heelenyc.im.common.entity.Header;
import com.heelenyc.im.common.entity.Message;
import com.heelenyc.im.common.entity.MessageType;



public class ClientLoginAuthReqHandler extends ChannelHandlerAdapter {

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;

        // 如果是握手应答消息，需要判断是否认证成功
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                // 握手失败，关闭连接
                ctx.close();
            } else {
                logger.info("Login is ok : " + message);
                ctx.fireChannelRead(msg);
            }
        } else
            ctx.fireChannelRead(msg);
    }

    private Message buildLoginReq() {
        Message message = new Message();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
