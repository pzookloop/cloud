package com.spc.product.service.impl;

import com.spc.product.bean.Product;
import com.spc.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {
        log.info("service-product: getProductById");
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("apple-" + productId);
        product.setNum(2);

        // TimeUnit.SECONDS.sleep(100);

        return product;
    }
}
