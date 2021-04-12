/**
 * @ClassName TimeServer
 * @Description TODO
 * @Author yins
 * @Date 2021-4-2
 * @Version 1.0
 **/
package com.yins.test.week03;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
