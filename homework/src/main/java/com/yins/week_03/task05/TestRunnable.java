/**
 * @ClassName TestRunnable
 * @Description TODO
 * @Author yins
 * @Date 2021-4-1
 * @Version 1.0
 **/
package com.yins.week_03.task05;

public class TestRunnable implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = Thread.currentThread();
        System.out.println("当前线程名称："+thread.getName());
    }
}
