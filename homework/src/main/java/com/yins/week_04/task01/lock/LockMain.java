/**
 * @ClassName LockMain
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.lock;

public class LockMain {
    public static void main(String[] args) {
        Count3 count3 = new Count3();
        ThreadA threadA = new ThreadA(count3);
        threadA.setName("thread A");
        threadA.start();

        ThreadB threadB = new ThreadB(count3);
        threadB.setName("thread B");
        threadB.start();
    }
}
