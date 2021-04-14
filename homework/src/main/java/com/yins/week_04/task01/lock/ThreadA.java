/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.lock;

public class ThreadA extends Thread{
    private Count3 count3;

    public ThreadA(Count3 count3) {
        this.count3 = count3;
    }

    @Override
    public void run() {
        count3.add();
    }
}
