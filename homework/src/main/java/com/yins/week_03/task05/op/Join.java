/**
 * @ClassName Join
 * @Description TODO
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05.op;

public class Join {
    public static void main(String[] args) {
        Object oo = new Object();
        MyThread myThread = new MyThread("thread1 -- ");
        myThread.setOo(oo);
        myThread.start();
        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        oo.wait(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "--" + i);
            }
        }
        oo.notifyAll();
    }
}

class MyThread extends Thread {
    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }
}
