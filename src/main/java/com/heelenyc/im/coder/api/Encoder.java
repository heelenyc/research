package com.heelenyc.im.coder.api;

import com.heelenyc.im.common.Constans;

import io.netty.buffer.ByteBuf;

/**
 * @author yicheng
 * @since 2014年9月19日
 * 
 */
public interface Encoder {
    
    static final byte[] LENGTH_PLACEHOLDER = new byte[Constans.MESSAGE_LENGTH_FIELD_LENGTH];
    void encode(Object msg, ByteBuf out) throws Exception;
}
