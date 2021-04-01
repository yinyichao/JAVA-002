package com.yins.week_03.task04;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName HttpServer02
 * @Description TODO
 * @Author yins
 * @Date 2021-3-28
 * @Version 1.0
 **/
public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);
        while(true){
            final  Socket socket = serverSocket.accept();
            new Thread(()->{
                service(socket);
            }).start();
        }
    }
    private static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio2";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
