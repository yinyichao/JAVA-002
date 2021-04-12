```plain
private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
```
修改
```plain
String value = "hello,netty";
```
改为
```plain
String value = HttpClient.get(url);
```
调用自定义的HttpClient类中的get方法，通过传入的url去请求后端服务器将返回的body值传回赋值到value中
```plain
public class HttpClient {
    public static String get(String url){
        String msg = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try{
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();
            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity entity = response.getEntity();
            msg = EntityUtils.toString(entity);
            System.out.println("响应内容为:" + msg);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
```
后端服务器中定义了如下内容
```plain
String body = "大家好，这是socketserver后端服务器！";
```
测试结果：
![图片](https://uploader.shimo.im/f/3NgMZACfJZkClmRd.png!thumbnail?fileGuid=Q8GkjqcppVtYV9kg)


