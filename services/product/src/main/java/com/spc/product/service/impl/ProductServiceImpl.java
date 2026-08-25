package com.spc.product.service.impl;

import com.spc.product.bean.Product;
import com.spc.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;


@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("apple-" + productId);
        product.setNum(2);
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException("超时");
//        }

        int a = 1 / 0; // 远程服务起不来, 超时, 异常都会走feign的兜底回调
        return product;
    }
}
