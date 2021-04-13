/**
 * @ClassName SyncCount
 * @Description TODO
 * @Author yins
 * @Date 2021-4-13
 * @Version 1.0
 **/
package com.yins.week_04.task01.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncCount {
    private int num = 0;
    private Lock lock = new ReentrantLock(true);

    public int add(){
        try{
            lock.lock();
            return num++;
        }finally {
            lock.unlock();
        }
    }
    public int getNum(){
        return num;
    }
}
