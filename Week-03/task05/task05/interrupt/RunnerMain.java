/**
 * @ClassName RunnerMain
 * @Description TODO
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05.interrupt;

public class RunnerMain {
    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Thread thread1 = new Thread(runner1);

        Runner2 runner2 = new Runner2();
        Thread thread2 = new Thread(runner2);

        thread1.start();
        thread2.start();

        thread2.interrupt();

        System.out.println(Thread.activeCount());
        Thread.currentThread().getThreadGroup().list();
        System.out.println(Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
        Thread.currentThread().getThreadGroup().getParent().list();
    }
}
