/**
 * @ClassName LongDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-13
 * @Version 1.0
 **/
package com.yins.week_04.task01.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongDemo {
    public static void main(String[] args) {
        final AtomicLong atomicLong = new AtomicLong();
        final LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                atomicLong.getAndIncrement();
                longAdder.increment();
            }).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("atomicLong ="+atomicLong.get());
        System.out.println("longAdder ="+longAdder.sum());
    }
}
