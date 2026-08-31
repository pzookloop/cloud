package com.spc.mail.controller;

import com.spc.mail.api.MailApi;
import com.spc.mail.dto.MailSendResult;
import com.spc.mail.dto.SendMailRequest;
import com.spc.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController implements MailApi {

    private final MailService mailService;

    @Override
    public MailSendResult send(SendMailRequest request) {
        return mailService.send(request);
    }
}
