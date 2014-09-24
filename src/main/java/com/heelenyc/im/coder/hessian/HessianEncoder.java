package com.heelenyc.im.coder.hessian;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;

import com.caucho.hessian.io.Hessian2Output;
import com.heelenyc.im.coder.api.Encoder;

/**
 * @author yicheng
 * @since 2014年9月19日
 * 
 */
public class HessianEncoder implements Encoder {


	/**
     * 
     */
	public HessianEncoder() {
	}

	@Override
	public void encode(Object msg, ByteBuf out) throws Exception {
		/*
		 * int lengthPos = out.writerIndex();
		 * out.writeBytes(LENGTH_PLACEHOLDER); ByteArrayOutputStream baos = new
		 * ByteArrayOutputStream(); output = new Hessian2Output(baos); try {
		 * output.writeObject(msg); output.flush();
		 * out.writeBytes(baos.toByteArray()); out.setInt(lengthPos,
		 * out.writerIndex() - lengthPos - 4); } finally { if (output != null) {
		 * output.close(); } }
		 */

		// begin position
		int lengthPos = out.writerIndex();
		// hold a 4 byte for the size
		out.writeBytes(LENGTH_PLACEHOLDER);
		// 写入数据实体
		Hessian2Output output = new Hessian2Output(new ByteBufOutputStream(out));
		output.writeObject(msg);
		output.flushBuffer();
		// write size
		out.setInt(lengthPos, out.writerIndex() - lengthPos - LENGTH_PLACEHOLDER.length);
		
		output.close();
	}

}
