package com.spc.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@EnableDiscoveryClient // 开启服务发现
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }

    // 1.项目启动就监听配置文件的变化
    // 2.拿到发生变化后的值
    // 3.做相关的业务处理
    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager manager) { // 项目一启动就启动runner, 给容器放一个bean
        return args -> {
            ConfigService configService = manager.getConfigService();
            configService.addListener("service-order.properties", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(5);
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("变化的信息: " + configInfo);
                    System.out.println("邮件通知.....");
                }
            });
            System.out.println("===============================");
        };
    }
}