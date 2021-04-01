package com.yins.week_03.task04.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {

    void filter(FullHttpRequest fullRequest);

}
