package com.spc.mail.feign;

import com.spc.mail.api.MailApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 供其他业务服务启用的邮件 Feign 客户端。
 * 调用方在 @EnableFeignClients(clients = MailFeignClient.class) 中注册即可。
 */
@FeignClient(value = "service-mail", contextId = "mail-feign-client")
public interface MailFeignClient extends MailApi {
}
