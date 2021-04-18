线程池：ThreadPoolExecutor

创建需要设置的一些参数：

1. int corePoolSize, 核心线程大小
2. int maximumPoolSize,最大线程大小
3. long keepAliveTime, 超过corePoolSize的线程多久不活动被销毁时间
4. TimeUnit unit,时间单位
5. BlockingQueue<Runnable> workQueue 任务队列
6. ThreadFactory threadFactory 线程池工厂
7. RejectedExecutionHandler handler 拒绝策略

java中的Executors类，提供了4种线程池，为了简化创建，四种线程池本质都是创建ThreadPoolExecutor类

1、newCachedThreadPool：用来创建一个可以无限扩大的线程池，适用于负载较轻的场景，执行短期异步任务。（可以使得任务快速得到执行，因为任务时间执行短，可以很快结束，也不会造成cpu过度切换）



2、newFixedThreadPool：创建一个固定大小的线程池，因为采用无界的阻塞队列，所以实际线程数量永远不会变化，适用于负载较重的场景，对当前线程数量进行限制。（保证线程数可控，不会造成线程过多，导致系统负载更为严重）



3、newSingleThreadExecutor：创建一个单线程的线程池，适用于需要保证顺序执行各个任务。

4、newScheduledThreadPool：适用于执行延时或者周期性任务。

## java.util.concurrent包中的阻塞队列

1. ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列
2. LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列（常用）
3. PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列
4. DelayQueue： 一个使用优先级队列实现的无界阻塞队列
5. SynchronousQueue： 一个不存储元素的阻塞队列（常用）
6. LinkedTransferQueue： 一个由链表结构组成的无界阻塞队列
7. LinkedBlockingDeque： 一个由链表结构组成的双向阻塞队列
* 一般如果线程池任务队列采用LinkedBlockingQueue队列的话，那么不会拒绝任何任务（因为其大小为Integer.MAX_VALUE），这种情况下，ThreadPoolExecutor最多仅会按照最小线程数corePoolSize来创建线程，也就是说线程池大小被忽略了。
* 如果线程池任务队列采用ArrayBlockingQueue队列，初始化设置了最大队列数。那么ThreadPoolExecutor的maximumPoolSize才会生效，那么ThreadPoolExecutor的maximumPoolSize才会生效会采用新的算法处理任务，
* 例如假定线程池的最小线程数为4，最大为8，ArrayBlockingQueue最大为10。随着任务到达并被放到队列中，线程池中最多运行4个线程（即核心线程数）直到队列完全填满，也就是说等待状态的任务小于等于10，ThreadPoolExecutor也只会利用4个核心线程线程处理任务。
* 如果队列已满，而又有新任务进来，此时才会启动一个新线程，这里不会因为队列已满而拒接该任务，相反会启动一个新线程。新线程会运行队列中的第一个任务，为新来的任务腾出空间。如果线程数已经等于最大线程数，任务队列也已经满了，则线程池会拒绝这个任务，默认拒绝策略是抛出异常。
* 这个算法背的理念是：该池大部分时间仅使用核心线程（4个），即使有适量的任务在队列中等待运行。这时线程池就可以用作节流阀。如果挤压的请求变得非常多，这时该池就会尝试运行更多的线程来清理；这时第二个节流阀—最大线程数就起作用了。

![图片](https://uploader.shimo.im/f/W4hHfdxUtJdD4y7k.png!thumbnail?fileGuid=3WtJvX9PPqgqTRv8)

java中Condition用法：

我们有时会遇到这样的场景：线程A执行到某个点的时候，因为某个条件condition不满足，需要线程A暂停；等到线程B修改了条件condition，使condition满足了线程A的要求时，A再继续执行。

## 自旋实现的等待通知

最简单的实现方法就是将condition设为一个volatile的变量，当A线程检测到条件不满足时就自旋，类似下面：

```plain
public class Test {
    private static volatile int condition = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!(condition == 1)) {
                    // 条件不满足，自旋
                }
                System.out.println("a executed");
            }
        });
        A.start();
        Thread.sleep(2000);
        condition = 1;
    }
}
```
这种方式的问题在于自旋非常耗费CPU资源，当然如果在自旋的代码块里加入Thread.sleep(time)将会减轻CPU资源的消耗，但是如果time设的太大，A线程就不能及时响应condition的变化，如果设的太小，依然会造成CPU的消耗。
## Object提供的等待通知

因此，java在Object类里提供了wait()和notify()方法，使用方法如下：

```plain
class Test1 {
    private static volatile int condition = 0;
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (!(condition == 1)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("a executed by notify");
                }
            }
        });
        A.start();
        Thread.sleep(2000);
        condition = 1;
        synchronized (lock) {
            lock.notify();
        }
    }
}
```
通过代码可以看出，在使用一个对象的wait()、notify()方法前必须要获取这个对象的锁。
当线程A调用了lock对象的wait()方法后，线程A将释放持有的lock对象的锁，然后将自己挂起，直到有其他线程调用notify()/notifyAll()方法或被中断。可以看到在lock.wait()前面检测condition条件的时候使用了一个while循环而不是if，那是因为当有其他线程把condition修改为满足A线程的要求并调用notify()后，A线程会重新等待获取锁，获取到锁后才从lock.wait()方法返回，而在A线程等待锁的过程中，condition是有可能再次变化的。

因为wait()、notify()是和synchronized配合使用的，因此如果使用了显示锁Lock，就不能用了。所以显示锁要提供自己的等待/通知机制，Condition应运而生。

## 显示锁提供的等待通知

我们用Condition实现上面的例子：

```plain
class Test2 {
    private static volatile int condition = 0;
    private static Lock lock = new ReentrantLock();
    private static Condition lockCondition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (!(condition == 1)) {
                        lockCondition.await();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
                System.out.println("a executed by condition");
            }
        });
        A.start();
        Thread.sleep(2000);
        condition = 1;
        lock.lock();
        try {
            lockCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
```
可以看到通过 lock.newCondition() 可以获得到 lock 对应的一个Condition对象lockCondition ，lockCondition的await()、signal()方法分别对应之前的Object的wait()和notify()方法。整体上和Object的等待通知是类似的。
上面我们看到了Condition实现的等待通知和Object的等待通知是非常类似的，而Condition提供的等待通知功能更强大，最重要的一点是，一个lock对象可以通过多次调用 lock.newCondition() 获取多个Condition对象，也就是说，在一个lock对象上，可以有多个等待队列，而Object的等待通知在一个Object上，只能有一个等待队列。


**ThreadLocal**<T>其实是与线程绑定的一个变量。ThreadLocal和Synchonized都用于解决多线程并发访问。但是ThreadLocal与synchronized有本质的区别。Synchronized用于线程间的数据共享，而ThreadLocal则用于线程间的数据隔离。Synchronized是利用锁的机制，使变量或代码块在某一时该只能被一个线程访问。而ThreadLocal为每一个线程都提供了变量的副本，使得每个线程在某一时间访问到的并不是同一个对象，这样就隔离了多个线程对数据的数据共享。而Synchronized却正好相反，它用于在多个线程间通信时能够获得数据共享。

一句话理解ThreadLocal，向ThreadLocal里面存东西就是向它里面的Map存东西的，然后ThreadLocal把这个Map挂到当前的线程底下，这样Map就只属于这个线程了。

Condition可以实现唤醒指定的部分线程await()、signal()，Object的wait()、notify()是随机唤醒，jdk1.6引入的**LockSupport**这个类 可以唤醒指定

```plain
LockSupport.park(); //一直wait
LockSupport.unpark(t); //指定t线程解除wait态
```


