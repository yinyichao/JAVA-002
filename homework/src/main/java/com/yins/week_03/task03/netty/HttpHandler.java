/**
 * @ClassName HttpHandler
 * @Description TODO
 * @Author yins
 * @Date 2021-4-1
 * @Version 1.0
 **/
package com.yins.week_03.task03.netty;

import com.yins.week_03.task03.filter.HeaderHttpResponseFilter;
import com.yins.week_03.task03.filter.HttpResponseFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    private HttpResponseFilter filter = new HeaderHttpResponseFilter();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        handlerTest(request,ctx);
    }
    public void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx){
        String value = "test filter";
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1,OK,Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type","application/json");
            response.headers().setInt("Content-Length",value.length());
            filter.filter(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1,NO_CONTENT);
        }finally {
            if(fullRequest != null){
                if(!HttpUtil.isKeepAlive(fullRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else {
                    response.headers().set(CONNECTION,KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
