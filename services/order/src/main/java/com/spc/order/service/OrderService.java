package com.spc.order.service;

import com.spc.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
