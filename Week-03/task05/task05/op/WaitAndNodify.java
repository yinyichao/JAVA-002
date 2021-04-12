/**
 * @ClassName WaitAndNodify
 * @Description TODO
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05.op;

public class WaitAndNodify {
    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        Thread t1 = new Thread(()->{
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        Thread t2 = new Thread(()->{
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}

class MethodClass {
    private final int MAX_COUNT = 20;
    int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount >= MAX_COUNT) {
                System.out.println("货舱已满，，不必再生产");

                wait();
            } else {
                productCount++;
            }
            notifyAll();
        }

    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(10);
            if (productCount <= 0) {
                System.out.println("货舱已无货。。。无法消费");
                wait();
            } else {
                productCount--;
            }
            notifyAll();
        }
    }
}
