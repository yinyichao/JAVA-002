/**
 * @ClassName test1
 * @Description TODO
 * @Author yins
 * @Date 2021-4-1
 * @Version 1.0
 **/
package com.yins.week_03.task05;

public class test1 {
    public static void main(String[] args) {
        /*run1()*/
        run2();
    }
    public static void run2(){
        Runnable runnable = new TestRunnable();
        Thread thread = new Thread(runnable);
        thread.setName("thread-2");
        thread.start();
    }
    public static void run1(){
        Runnable runnable = ()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread thread = Thread.currentThread();
            System.out.println("当前线程名称:"+thread.getName());
        };
        Thread thread = new Thread(runnable);
        thread.setName("thread-1");
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
