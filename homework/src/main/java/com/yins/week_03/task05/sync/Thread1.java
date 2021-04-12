/**
 * @ClassName Thread1
 * @Description TODO
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05.sync;

public class Thread1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread ta = new Thread(t1,"A");
        Thread tb = new Thread(t1,"B");
        ta.start();
        tb.start();
    }
}
