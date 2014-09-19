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

    Hessian2Input input;

    /**
     * 
     */
    public HessianDecoder() {
        input = new Hessian2Input(null);
    }

    @Override
    public Object decode(ByteBuf in) throws Exception {

        // read size
        int objectSize = in.readInt();
        // slice
        ByteBuf buf = in.slice(in.readerIndex(), objectSize);

        input.init(new ByteBufInputStream(buf));
        Object obj = input.readObject();
        // set index for next
        in.readerIndex(in.readerIndex() + objectSize);
        
        return obj;
    }

}
