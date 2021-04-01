/**
 * @ClassName HttpHandler
 * @Description TODO
 * @Author yins
 * @Date 2021-4-1
 * @Version 1.0
 **/
package com.yins.week_03.task04.netty;

import com.yins.week_03.task01.HttpClient;
import com.yins.week_03.task04.filter.HeaderHttpResponseFilter;
import com.yins.week_03.task04.filter.HttpResponseFilter;
import com.yins.week_03.task04.router.HttpEndpointRouter;
import com.yins.week_03.task04.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    private HttpEndpointRouter router = new RandomHttpEndpointRouter();
    private final List<String> proxyServer;
    private HttpResponseFilter filter = new HeaderHttpResponseFilter();

    public HttpHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        handlerTest(request,ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String url = router.route(proxyServer);
        FullHttpResponse response = null;
        try {

            String value = HttpClient.get(url);

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            filter.filter(response);
        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        exceptionCaught(ctx, cause);
    }
}
