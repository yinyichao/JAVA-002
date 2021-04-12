/**
 * @ClassName Counter
 * @Description TODO
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05.sync;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    public final static int A = 10;
    public static int B = 10;
    private volatile int sum = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    public void incr() {
        sum = sum + 1;
    }
    public void incrAtomicInteger(){
        count.incrementAndGet();
    }

    public int getSum() {
        return sum;
    }
    public int getCount(){
        return count.intValue();
    }

    public static void main(String[] args) throws InterruptedException {
        int loop = 10_0000;
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }
        System.out.println("single thread: " + counter.getSum());
        final Counter counter1 = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter1.incr();
                counter1.incrAtomicInteger();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter1.incr();
                counter1.incrAtomicInteger();
            }
        }, "t2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("multiple threads: " + counter1.getSum());
        System.out.println("multiple threads: " + counter1.getCount());
    }
}
