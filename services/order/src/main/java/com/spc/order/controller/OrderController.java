package com.spc.order.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.spc.order.bean.Order;
import com.spc.order.properties.OrderProperties;
import com.spc.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@RefreshScope // 自动刷新来自nacos的配置
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @Value("${order.timeout}")
//    String orderTimeout;
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

//    链路流控测试
    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Order seckill(@RequestParam(value = "userId", required = false) Long userId, // 请求参数如果携带则参与流控, 不携带则不流控
                             @RequestParam(value = "productId", defaultValue = "1000") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    // 这个方法只能作为SentinelResource中blockHandler, 如果SentinelResource要用fallback, 则需要seckillFallback的异常由BlockException变为Throwable
    public Order seckillFallback(Long userId,
                                 Long productId,
                                 Throwable e) {
        log.info("+++++++热点参数测试, seckillFallback兜底回调调用+++++++");
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("异常信息: " + e.getCause());
        return order;
    }

    @GetMapping("/writeDb")
    public String write() {
        return "write DB success";
    }

    @GetMapping("/readDb")
    public String readDb() {
        return "read DB success";
    }


    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String config() {
        return "order.timeout" + orderProperties.getTimeout() +
                ", order.auto-confirm" + orderProperties.getAutoConfirm() +
                ", db url: " + orderProperties.getDbUrl();
    }
}
