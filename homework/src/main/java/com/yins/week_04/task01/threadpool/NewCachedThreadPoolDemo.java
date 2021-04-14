/**
 * @ClassName NewCachedThreadPoolDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-13
 * @Version 1.0
 **/
package com.yins.week_04.task01.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i =0;i<1_0000;i++){
            final int no = i;
            Runnable runnable = ()->{
                try {
                    System.out.println("start:"+no);
                    Thread.sleep(1000);
                    System.out.println("end:"+no);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
        System.out.println("Main Thread end -------------------------------------");
    }
}
