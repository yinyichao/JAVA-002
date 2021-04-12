/**
 * @ClassName ClientHandler
 * @Description TODO
 * @Author yins
 * @Date 2021-3-30
 * @Version 1.0
 **/
package com.yins.week_03.task02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static String result;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            FullHttpResponse fullRequest = (FullHttpResponse) msg;
            ByteBuf bb = fullRequest.content();
            byte[] respByte = new byte[bb.readableBytes()];
            bb.readBytes(respByte);
            String respStr = new String(respByte);
            result = respStr;

        } finally{
            // 必须释放msg数据
            ReferenceCountUtil.release(msg);

        }

    }

    public static String getResult() {
        return result;
    }

    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端读取数据完毕");
        ctx.flush();
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client 读取数据出现异常");
        ctx.close();
    }

}
