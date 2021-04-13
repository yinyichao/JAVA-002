/**
 * @ClassName NewCachedThreadPoolDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-13
 * @Version 1.0
 **/
package com.yins.week_03.task01.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        
    }
}
