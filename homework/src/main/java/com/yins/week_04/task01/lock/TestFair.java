/**
 * @ClassName TestFair
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class TestFair {
    public static volatile int race = 0;
    public static ReentrantLock lock = new ReentrantLock(true);

    public static void increase() {
        lock.lock();
        race++;
        lock.unlock();
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        int count = Thread.activeCount();
        long now = System.currentTimeMillis();
        System.out.println(count);
        AtomicReference<Thread> sign = new AtomicReference<>();
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10_0000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > count) {
            Thread.yield();
        }
        System.out.println(lock.getClass().getName() + " ts = " + (System.currentTimeMillis() - now));
        System.out.println(race);
    }
}
