package com.yins.week_03.task04.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("yins", "this is test");
    }
}
