/**
 * @ClassName SynchronousQueueDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.test.week04;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>(true);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("put thread start");
                    try {
                        queue.put(Integer.valueOf(Thread.currentThread().getName()));
                    } catch (InterruptedException e) {
                    }
                    System.out.println("put thread end");
                }
            },""+i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("take thread start");
                    try {
                        System.out.println("take from putThread: " + queue.take());
                    } catch (InterruptedException e) {
                    }
                    System.out.println("take thread end");
                }
            }).start();
        }

    }
}
