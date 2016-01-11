package com.heelenyc.im.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.heelenyc.im.common.Constans;
import com.heelenyc.im.common.entity.Header;
import com.heelenyc.im.common.entity.Message;
import com.heelenyc.im.common.entity.MessageType;

public class ServerLoginAuthRespHandler extends ChannelHandlerAdapter {

    private Log logger = LogFactory.getLog(this.getClass());

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
    private List<String> whitekList = Arrays.asList(Constans.WHITELIST);
    private List<String> blackList = Arrays.asList(Constans.BLACKLIST);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        // 如果是握手请求消息，处理，其它消息透传
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            Message loginResp = null;
            // 重复登陆，拒绝
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOK = IsAllowed(ip);
                loginResp = isOK ? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (isOK)
                    nodeCheck.put(nodeIndex, true);
            }
            logger.info("The login response is : " + loginResp + " body [" + loginResp.getBody() + "]");
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * @param ip
     * @return
     */
    private boolean IsAllowed(String ip) {

        if (Constans.IS_WHITELIST_FIRST) {
            if (whitekList.contains(ip)) {
                return true;
            }
            if (blackList.contains(ip)) {
                return false;
            }
        } else {
            if (blackList.contains(ip)) {
                return false;
            }
            if (whitekList.contains(ip)) {
                return true;
            }
        }
        return Constans.IS_OTHER_ALLOWED;
    }

    private Message buildResponse(byte result) {
        Message message = new Message();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        nodeCheck.remove(ctx.channel().remoteAddress().toString());// 删除缓存
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }
}
