package com.spc.order.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 调用第三方服务, 直接写ip即可
@FeignClient(value = "mock-client", url = "http://localhost:8000/")
public interface MockUrlFeignClient {
    @GetMapping("/create")
    String getProduct(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) ;
}
