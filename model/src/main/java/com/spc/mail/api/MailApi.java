package com.spc.mail.api;

import com.spc.mail.dto.MailSendResult;
import com.spc.mail.dto.SendMailRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 邮件服务的共享 HTTP 契约。调用方创建 FeignClient 后继承此接口即可。
 */
@RequestMapping("/mail")
public interface MailApi {

    @PostMapping("/send")
    MailSendResult send(@Valid @RequestBody SendMailRequest request);
}
