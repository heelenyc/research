package com.heelenyc.im.coder.api;

import io.netty.buffer.ByteBuf;

/**
 * @author yicheng
 * @since 2014年9月19日
 *
 */
public interface Decoder {

    /**
     * @param in
     * @return
     * @throws Exception
     */
    Object decode(ByteBuf in) throws Exception;
}
