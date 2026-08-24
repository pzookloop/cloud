package com.spc.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient client; // spring提供

    @Autowired
    NacosServiceDiscovery nacosClient;

    @Test
    void nacosDiscoveryTest() throws NacosException {
        for (String service : nacosClient.getServices()) {
            System.out.println("service: " + service);
            for (ServiceInstance instance : nacosClient.getInstances(service)) {
                System.out.println("ip: " + instance.getHost() + ", port: " + instance.getPort());
            }
        }
    }



    @Test
    void discoveryTest() {
        for (String service : client.getServices()) {
            System.out.println("service: " + service);
            for (ServiceInstance instance : client.getInstances(service)) {
                System.out.println("ip: " + instance.getHost() + ", port: " + instance.getPort());
            }
        }
    }
}
