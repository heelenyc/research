package com.heelenyc.im.coder.hessian;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import com.caucho.hessian.io.Hessian2Input;
import com.heelenyc.im.coder.api.Decoder;

/**
 * @author yicheng
 * @since 2014年9月19日
 * 
 */
public class HessianDecoder implements Decoder {

    /**
     * 
     */
    public HessianDecoder() {
    }

    @Override
    public Object decode(ByteBuf in) throws Exception {

        /*
         * int objectSize = in.readInt(); ByteBuf buf =
         * in.slice(in.readerIndex(), objectSize); byte[] byteArray = new
         * byte[buf.capacity()]; buf.readBytes(byteArray, 0, buf.capacity());
         * input = new Hessian2Input(new ByteArrayInputStream(byteArray));
         * Object obj = input.readObject(); in.readerIndex(in.readerIndex() +
         * objectSize);
         */

        // read size
        int objectSize = in.readInt();
        Hessian2Input input = new Hessian2Input(new ByteBufInputStream(in, objectSize));
        Object obj = input.readObject();
        input.close();
        return obj;
    }

}
