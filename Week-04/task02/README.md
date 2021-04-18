思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程?

1、使用thread.join等待线程执行完成，拿到结果

2、使用CountDownLatch等待记数完成，拿到结果

3、使用Callable、FutureTask获取执行结果




