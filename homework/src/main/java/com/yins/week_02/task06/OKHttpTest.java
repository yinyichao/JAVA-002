package com.yins.week_02.task06;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName OKHttpTest
 * @Description TODO
 * @Author yins
 * @Date 2021-3-28
 * @Version 1.0
 **/
public class OKHttpTest {
    public static void main(String[] args) throws IOException {
        /*String url = "http://192.168.199.167:8801";
        //OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient okHttpClient = new OkHttpClient();

        final Request request = new Request.Builder().url(url).build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override

            public void onFailure(Call call, IOException e) {
                System.out.println("failure:" + call);

            }

            @Override

            public void onResponse(Call call, Response response) throws
                    IOException {

                System.out.println(response.body().string());

            }

        });*/
        OKHttpTest t = new OKHttpTest();
        t.onHttp();
    }
    private String onHttp(){
        //配置OkHttp
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        Request.Builder requestBuilder = new Request.Builder();
        //配置url
        requestBuilder.url("http://127.0.0.1:8808/test");
        //配置请求方式
        requestBuilder.get();
        try {
            //发送网络请求
            Response response = client.newCall(requestBuilder.build()).execute();
            String result = response.body().string();
            System.out.println("打印, 响应时间：" +result);
            return result;
        } catch (IOException e) {
            System.out.println("打印"+e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
