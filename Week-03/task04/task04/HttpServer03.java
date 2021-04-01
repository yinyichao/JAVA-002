package com.yins.week_03.task04;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName HttpServer03
 * @Description TODO
 * @Author yins
 * @Date 2021-3-28
 * @Version 1.0
 **/
public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final ServerSocket serverSocket = new ServerSocket(8803);
        while(true){
            Socket socket = serverSocket.accept();
            executorService.execute(()->{
                service(socket);
            });
        }
    }
    private static void service(Socket socket){
        try{
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio3";
            printWriter.println("Content-Length:"+body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
