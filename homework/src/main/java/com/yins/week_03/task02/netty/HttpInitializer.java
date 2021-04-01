/**
 * @ClassName HttpInitializer
 * @Description TODO
 * @Author yins
 * @Date 2021-3-30
 * @Version 1.0
 **/
package com.yins.week_03.task02.netty;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpServerCodec());

        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));

        channelPipeline.addLast(new HttpHandler());
    }
}
