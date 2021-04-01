```plain
private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
```
修改
```plain
String value = "hello,netty";
```
改为
```plain
NettyHttpClient client = new NettyHttpClient("127.0.0.1",8803);
String value = client.action();
```
NettyHttpClient中action代码如下：
```plain
public String action() {
    EventLoopGroup boss = new NioEventLoopGroup();
    Bootstrap b = new Bootstrap();
    b.group(boss);
    b.channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE,true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline channelPipeline = socketChannel.pipeline();
                    channelPipeline.addLast(new HttpResponseDecoder());
                    channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                    channelPipeline.addLast(new ClientHandler());
                }
            });
    // 客户端开启
    ChannelFuture cf = null;
    String msg = null;
    try {
        cf = b.connect(ip, port).sync();
        String reqStr = "test";
        // 发送客户端的请求
        cf.channel().writeAndFlush(Unpooled.copiedBuffer(reqStr.getBytes()));
        while(msg==null){
            msg = ClientHandler.getResult();
        }
        // 等待直到连接中断
        cf.channel().closeFuture().sync();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return msg;
}
```
由于Netty是异步I/O，故此处使用循环获取服务器的响应数据，暂时没想到其他的解决方案。
```plain
while(msg==null){
            msg = ClientHandler.getResult();
        }
```
ClientHandler类代码如下：
```plain
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static String result;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpResponse fullRequest = (FullHttpResponse) msg;
            ByteBuf bb = fullRequest.content();
            byte[] respByte = new byte[bb.readableBytes()];
            bb.readBytes(respByte);
            String respStr = new String(respByte);
            result = respStr;
        } finally{
            // 必须释放msg数据
            ReferenceCountUtil.release(msg);
        }
    }
    public static String getResult() {
        return result;
    }
    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端读取数据完毕");
        ctx.flush();
    }
    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client 读取数据出现异常");
        ctx.close();
    }
}
```
后端服务器使用netty去请求socket server，将返回的数据响应回浏览器展示
[http://127.0.0.1:8808/test](http://127.0.0.1:8808/test?fileGuid=WdJjpxH8TpCdDPvC)

展示为：

![图片](https://uploader.shimo.im/f/QIlNxrdiUOo70FkX.png!thumbnail?fileGuid=WdJjpxH8TpCdDPvC)

