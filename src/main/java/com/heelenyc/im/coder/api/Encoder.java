package com.heelenyc.im.coder.api;

import io.netty.buffer.ByteBuf;

/**
 * @author yicheng
 * @since 2014年9月19日
 * 
 */
public interface Encoder {
    
    static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    void encode(Object msg, ByteBuf out) throws Exception;
}
