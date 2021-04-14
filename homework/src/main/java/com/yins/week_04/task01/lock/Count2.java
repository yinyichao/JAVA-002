/**
 * @ClassName Count2
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Count2 {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void get() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get begin");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put() {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " put start");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
