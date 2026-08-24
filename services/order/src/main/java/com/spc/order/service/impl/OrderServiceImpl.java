package com.spc.order.service.impl;

import com.spc.order.service.OrderService;
import com.spc.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import com.spc.order.bean.Order;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        order.setId(1L);
//        Product product = productFeignClient.getProductById(productId);
//        Product product = getProductFromRemote(productId);
        Product product = getProductFromRemoteWithLoadBalancer(productId);
        BigDecimal totalAmount = product.getPrice().multiply(new BigDecimal(product.getNum()));
        order.setTotalAmount(totalAmount);
        order.setTotalAmount(new BigDecimal(90));
        order.setUserId(userId);
        order.setNickname("abbc");
        order.setAddress("shanghai");
        order.setProductList(List.of(product));
        return order;
    }

    private Product getProductFromRemote(Long productId) {
        // 1.获取所有商品服务所在机器的ip+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String uri = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        // 发请求
        log.info("远程请求: {}", uri);
        Product product = restTemplate.getForObject(uri, Product.class);
        log.info("远程请求成功: {}", product);
        return product;
    }

    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        // 1.获取所有商品服务所在机器的ip+port
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String uri = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        // 发请求
        log.info("远程请求: {}", uri);
        Product product = restTemplate.getForObject(uri, Product.class);
        log.info("远程请求成功: {}", product);
        return product;
    }
}
