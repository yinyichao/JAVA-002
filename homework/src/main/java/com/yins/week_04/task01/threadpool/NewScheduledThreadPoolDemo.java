/**
 * @ClassName NewScheduledThreadPoolDemo
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(16);
        for (int i = 0; i < 100; i++) {
            final int no = i;
            service.schedule(() -> {
                System.out.println("start:" + no);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end:" + no);
            },10, TimeUnit.SECONDS);
        }
        service.shutdown();
        System.out.println("Main Thread End----------");
    }
}
