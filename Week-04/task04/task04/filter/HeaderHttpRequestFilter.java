package com.yins.week_03.task04.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest) {
        fullRequest.headers().set("yins", "this is test");
    }
}
