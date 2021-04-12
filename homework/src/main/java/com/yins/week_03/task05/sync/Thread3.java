/**
 * @ClassName Thread3
 * @Description TODO
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05.sync;

public class Thread3 {
    class Inner {
        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void m4t1(Inner inner){
        synchronized(inner){
            inner.m4t1();
        }
    }
    private void m4t2(Inner inner){
        inner.m4t2();
    }

    public static void main(String[] args) {
        final Thread3 thread3 = new Thread3();
        final Inner inner = thread3.new Inner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread3.m4t1(inner);
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread3.m4t2(inner);
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
