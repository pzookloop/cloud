package com.spc.order.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class XTokenRequestInterceptor implements RequestInterceptor { // 默认每个远程调用生效生效
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("feign请求拦截器启动");
        template.header("X-Token", UUID.randomUUID().toString());
    }
}
