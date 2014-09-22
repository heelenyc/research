package com.heelenyc.im.coder.hessian;

import io.netty.buffer.ByteBuf;

import java.io.ByteArrayOutputStream;

import com.caucho.hessian.io.Hessian2Output;
import com.heelenyc.im.coder.api.Encoder;

/**
 * @author yicheng
 * @since 2014年9月19日
 * 
 */
public class HessianEncoder implements Encoder {
    
    Hessian2Output output;
    
    /**
     * 
     */
    public HessianEncoder() {
        //output = new Hessian2Output(null);
    }

    @Override
    public void encode(Object msg, ByteBuf out) throws Exception {
        // begin position
        int lengthPos = out.writerIndex();
        //  hold a 4 byte for the size 
        out.writeBytes(LENGTH_PLACEHOLDER);
        // 写入数据实体
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        output = new Hessian2Output(baos);
        try {
            output.writeObject(msg);
            output.flush();
            out.writeBytes(baos.toByteArray());
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

}
