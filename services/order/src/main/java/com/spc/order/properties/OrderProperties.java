package com.spc.order.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "order") // 在nacos环境下无需refreshScope就可以自动刷新
public class OrderProperties {
    String timeout;

    String autoConfirm;

    String dbUrl;
}
