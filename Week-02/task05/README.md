![图片](https://uploader.shimo.im/f/dycnu5zmtCivkQBg.png!thumbnail?fileGuid=hykpRrhvPDGjhccJ)

之前一直没留意过java中的socket到底是什么，感觉像是TCP、IP簇，但是又觉得不太对，找到了这个图。Socket是应用层与TCP/IP协议族通信的中间抽象层，它是一组接口，应用层通过调用这些接口实现发送和接收数据。一般这种抽象层由操作系统提供或者由JVM自己实现。

```plain
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            Socket socket = serverSocket.accept();
            service(socket);
        }
    }
    private static void service(Socket socket) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
HttpServer01中使用ServerSocket监听8801端口，service(Socket socket)方法通过模拟http响应报文，将body = "hello,nio1"返回请求端。使用serverSocket.accept()；开启监听，循环监听8801端口。使用浏览器访问[http://127.0.0.1:8801/](http://127.0.0.1:8801/?fileGuid=hykpRrhvPDGjhccJ)，会发送http的请求包，到达8801端口的程序之后，程序会从请求的socket中获取outputStream，然后将http响应报文返回请求端（浏览器），浏览器解析数据包，展示内容。
![图片](https://uploader.shimo.im/f/u7h68icaUSSJtJgW.png!thumbnail?fileGuid=hykpRrhvPDGjhccJ)

```plain
public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);
        while(true){
            final  Socket socket = serverSocket.accept();
            new Thread(()->{
                service(socket);
            }).start();
        }
    }
    private static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio2";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
HttpServer02中引入了其他工作线程，当有请求到达的时候，每次都启动一个新的线程对其进行处理。这样就可以很好地支持并发请求，HttpServer01中只有一个监听线程，当多个请求同时到达的时候就只能等待，第一个请求处理完成才能处理第二个，但是HttpServer02中当第一个请求到达时，可以开启一个工作线程对其后续处理，监听线程可以马上接受第二个请求。这样可以极大的增大处理速度，因为毕竟处理请求比较耗时。
```plain
public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final ServerSocket serverSocket = new ServerSocket(8803);
        while(true){
            Socket socket = serverSocket.accept();
            executorService.execute(()->{
                service(socket);
            });
        }
    }
    private static void service(Socket socket){
        try{
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio3";
            printWriter.println("Content-Length:"+body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
```
HttpServer03在HttpServer02的 基础上增加了线程池，对工作线程进行了管理，HttpServer02由于每次处理请求都需要创建新的线程，结束之后又需要销毁线程，线程的创建是很消耗资源的一件事，所以HttpServer03使用了线程池之后，同时创建一部分线程，不销毁线程，让请求共享线程池里的工作线程。
