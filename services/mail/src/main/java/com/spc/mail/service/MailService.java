package com.spc.mail.service;

import com.spc.mail.dto.MailSendResult;
import com.spc.mail.dto.SendMailRequest;

public interface MailService {
    MailSendResult send(SendMailRequest request);
}
