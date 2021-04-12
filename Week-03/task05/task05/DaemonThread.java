/**
 * @ClassName DaemonThread
 * @Description 主线程是不会等待守护线程执行完在结束的，
 * 当只有守护线程的时候，主线程执行结束会直接关闭进程，所以当Daemon线程执行Thread.sleep(1000)时，
 * 主线程是不会等待直接结束，所以控制台不会打印输出
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05;

public class DaemonThread {
    public static void main(String[] args) {
        Runnable task =()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println("当前线程："+t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true);
        thread.start();
    }
}
