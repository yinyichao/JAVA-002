/**
 * @ClassName GCLogAnalysis
 * @Description TODO
 * @Author yins
 * @Date 2021-3-23
 * @Version 1.0
 **/
package com.yins.test.week02;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class GCLogAnalysis {
    private static Random random = new Random();

    public static void main(String[] args) {
        //开始时间
        long startMillis = System.currentTimeMillis();
        //设置执行1秒
        long timeoutMillis = TimeUnit.SECONDS.toMillis(1);
        //结束时间
        long endMillis = startMillis + timeoutMillis;
        //计数器
        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");

        int cacheSize = 200;
        Object[] cacedGarbage = new Object[cacheSize];

        while (System.currentTimeMillis() < endMillis) {
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize) {
                //引用 不回收  进入老年代  200个对象
                cacedGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束！共生成对象次数：" + counter.longValue());
    }

    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result = builder.toString();
                break;
        }
        return result;
    }
}
