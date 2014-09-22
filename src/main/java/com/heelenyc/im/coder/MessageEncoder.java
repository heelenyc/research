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
package com.heelenyc.im.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.heelenyc.im.coder.api.Encoder;
import com.heelenyc.im.coder.hessian.HessianEncoder;
import com.heelenyc.im.common.entity.Message;


public final class MessageEncoder extends MessageToByteEncoder<Message> {

    private Encoder encoder;
    private Log logger =LogFactory.getLog(this.getClass());

    public MessageEncoder() throws IOException {
        // get a encoder impl
        this.encoder = new HessianEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf sendBuf) throws Exception {
        if (msg == null || msg.getHeader() == null)  
            throw new Exception("The encode message is null");
        
        sendBuf.writeInt((msg.getHeader().getCrcCode()));
        sendBuf.writeInt((msg.getHeader().getLength()));
        sendBuf.writeLong((msg.getHeader().getSessionID()));
        sendBuf.writeByte((msg.getHeader().getType()));
        sendBuf.writeByte((msg.getHeader().getPriority()));
        sendBuf.writeInt((msg.getHeader().getAttachment().size()));
        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            encoder.encode(value, sendBuf);
        }
        key = null;
        keyArray = null;
        value = null;
        if (msg.getBody() != null) {
            encoder.encode(msg.getBody(), sendBuf);
        } else
            sendBuf.writeInt(0);
        // 写入消息总长度
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
        
        logger.debug("encode :" + msg);
    }
}
