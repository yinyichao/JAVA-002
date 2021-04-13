/**
 * @ClassName AtomicCount
 * @Description TODO
 * @Author yins
 * @Date 2021-4-13
 * @Version 1.0
 **/
package com.yins.week_04.task01.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCount {
    private AtomicInteger  atomicInteger = new AtomicInteger();

    public void add(){
        atomicInteger.getAndIncrement();
    }
    public int get(){
        return atomicInteger.get();
    }
}
