/**
 * @ClassName ExceptionDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-13
 * @Version 1.0
 **/
package com.yins.week_04.task01.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExceptionDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            Future<Double> future = executorService.submit(()->{
                throw new RuntimeException("executorService.submit()");
            });
            double b = future.get();
            System.out.println(b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("catch submit");
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main Thread End!");
    }
}
