/**
 * @ClassName NettyHttpClient
 * @Description TODO
 * @Author yins
 * @Date 2021-3-30
 * @Version 1.0
 **/
package com.yins.week_03.task02;

import com.yins.week_03.task02.netty.HttpHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpServerCodec;


public class NettyHttpClient {
    private String ip;
    private int port;

    public NettyHttpClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String action() {
        EventLoopGroup boss = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(boss);
        b.channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        channelPipeline.addLast(new HttpResponseDecoder());
                        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                        channelPipeline.addLast(new ClientHandler());
                    }
                });
        // 客户端开启
        ChannelFuture cf = null;
        String msg = null;
        try {
            cf = b.connect(ip, port).sync();
            String reqStr = "我是客户端请求1$_";

            // 发送客户端的请求
            cf.channel().writeAndFlush(Unpooled.copiedBuffer(reqStr.getBytes()));
            while(msg==null){
                msg = ClientHandler.getResult();
            }
            // 等待直到连接中断
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public static void main(String[] args) {
        NettyHttpClient client = new NettyHttpClient("127.0.0.1",8803);
        System.out.println(client.action());
    }
}
