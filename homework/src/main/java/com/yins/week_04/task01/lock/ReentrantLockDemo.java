/**
 * @ClassName ReentrantLockDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.lock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        final Count count = new Count();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                count.get();
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                count.put();
            }).start();
        }
    }
}
