package com.spc.order.service.impl;

import com.spc.order.service.OrderService;
import org.springframework.stereotype.Service;
import com.spc.order.bean.Order;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        order.setId(1L);
        // Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
//        Product product = productFeignClient.getProductById(productId);
//        BigDecimal totalAmount = product.getPrice().multiply(new BigDecimal(product.getNum()));
//        order.setTotalAmount(totalAmount);
        order.setTotalAmount(new BigDecimal(90));
        order.setUserId(userId);
        order.setNickname("abbc");
        order.setAddress("shanghai");
//        order.setProductList(List.of(product));
        order.setProductList(List.of());
        return order;
    }
}
