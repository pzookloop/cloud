package com.spc.order.feign;


import com.spc.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "service-product") // feign客户端
public interface ProductFeignClient {
    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable("id") Long productId);
}
