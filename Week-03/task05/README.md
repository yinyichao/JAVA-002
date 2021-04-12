```plain
System.out.println(Thread.activeCount());
```
4
```plain
Thread.currentThread().getThreadGroup().list();
```
java.lang.ThreadGroup[name=main,maxpri=10]
Thread[main,5,main]

Thread[Monitor Ctrl-Break,5,main]

Thread[Thread-0,5,main]

Thread[Thread-1,5,main]

```plain
System.out.println(Thread.currentThread().getThreadGroup().getParent().activeGroupCount());
```
1
```plain
Thread.currentThread().getThreadGroup().getParent().list();
```
java.lang.ThreadGroup[name=system,maxpri=10]
Thread[Reference Handler,10,system]

Thread[Finalizer,8,system]

Thread[Signal Dispatcher,9,system]

Thread[Attach Listener,5,system]

java.lang.ThreadGroup[name=main,maxpri=10]

Thread[main,5,main]

Thread[Monitor Ctrl-Break,5,main]

Thread[Thread-0,5,main]

Thread[Thread-1,5,main]

```plain
boolean result = Thread.currentThread().isInterrupted();
boolean result1 = Thread.interrupted();
boolean result3 = Thread.currentThread().isInterrupted();
System.out.println("Runner2.run result ===>" + result);
System.out.println("Runner2.run result1 ===>" + result1);
System.out.println("Runner2.run result3 ===>" + result3);
```
true；true；false；
```plain
thread2.interrupt();
```
isInterrupted()获取到thread2.interrupt()设置的中断状态：true
interrupted()获取到thread2.interrupt()设置的中断状态：true，但是会将状态清除：false

在第二次调用isInterrupted()时，获取到的就是false了

wait(0); //如果 timeout 为零，则不考虑实际时间，在获得通知前该线程将一直等待。

```plain
/**
 * @ClassName Thread3
 * @Description TODO
 * @Author yins
 * @Date 2021-4-12
 * @Version 1.0
 **/
package com.yins.week_03.task05.sync;
public class Thread3 {
    class Inner {
        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void m4t1(Inner inner){
        synchronized(inner){
            inner.m4t1();
        }
    }
    private void m4t2(Inner inner){
        inner.m4t2();
    }
    public static void main(String[] args) {
        final Thread3 thread3 = new Thread3();
        final Inner inner = thread3.new Inner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread3.m4t1(inner);
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                thread3.m4t2(inner);
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
```
t1 : Inner.m4t1()=4
t2 : Inner.m4t2()=4

t2 : Inner.m4t2()=3

t1 : Inner.m4t1()=3

t1 : Inner.m4t1()=2

t2 : Inner.m4t2()=2

t2 : Inner.m4t2()=1

t1 : Inner.m4t1()=1

t2 : Inner.m4t2()=0

t1 : Inner.m4t1()=0

**1、当锁的是非静态的内置类对象时，只会阻塞访问同一个内置类对象（同一个外置类对象创建的同一个内置类）方法的线程。**

**2、当锁的是内置类的方法时，会阻塞访问同一个内置类对象的其他同步方法的线程。**

3、当**锁的是静态内置类时，锁的是这个内置类（静态内置类的创建不依赖于外部类对象），会阻塞所有访问该静态内置类的线程**


