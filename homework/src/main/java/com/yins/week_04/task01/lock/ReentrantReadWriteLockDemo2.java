/**
 * @ClassName ReentrantReadWriteLockDemo2
 * @Description TODO
 * @Author yins
 * @Date 2021-4-14
 * @Version 1.0
 **/
package com.yins.week_04.task01.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo2 {
    private final Map<String,Object> map = new HashMap<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public Object readWrite(String key){
        Object value = null;
        System.out.println("1.首先开启读缩去缓存中取数据");
        rwLock.readLock().lock();
        try{
            value = map.get(key);
            if(value == null){
                System.out.println("2.数据不存在，则释放读缩，开启写锁");
                rwLock.readLock().unlock();
                rwLock.writeLock().lock();
                try{
                    if(value == null){
                        value = "aaaa";
                    }
                }finally {
                    System.out.println("3.释放写锁");
                    rwLock.writeLock().unlock();
                }
                System.out.println("4.开启读锁");
                rwLock.readLock().lock();
            }
        }finally {
            System.out.println("5.释放读锁");
            rwLock.readLock().unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo2 demo2 = new ReentrantReadWriteLockDemo2();
        demo2.readWrite("hello world");
    }
}
