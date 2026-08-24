package com.spc.order.feign;


import com.spc.order.feign.fallback.ProductFeignClientFallback;
import com.spc.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "service-product", contextId = "product-feign-client", fallback = ProductFeignClientFallback.class) // feign客户端
public interface ProductFeignClient {
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long productId);
}
