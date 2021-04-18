/**
 * @ClassName ReentrantReadWriteLockDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.lock;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        final Count2 count2 = new Count2();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                count2.get();
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                count2.put();
            }).start();
        }
    }
}
