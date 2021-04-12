/**
 * @ClassName HttpInitializer
 * @Description TODO
 * @Author yins
 * @Date 2021-3-29
 * @Version 1.0
 **/
package com.yins.week_03.task01.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 配置链式解码器
        ChannelPipeline p = socketChannel.pipeline();
        // 解码成HttpRequest
        p.addLast(new HttpServerCodec());
        // 解码成FullHttpRequest
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        // 添加处自定义的处理器
        p.addLast(new HttpHandler());
    }
}
