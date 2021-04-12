/**
 * @ClassName NioClient
 * @Description TODO
 * @Author yins
 * @Date 2021-4-8
 * @Version 1.0
 **/
package com.yins.test.week03.test1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()) {


            int port = 8080;
            socketChannel.connect(new InetSocketAddress("127.0.0.1", port));
            socketChannel.configureBlocking(false);

            String msg = "测试一下";
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
