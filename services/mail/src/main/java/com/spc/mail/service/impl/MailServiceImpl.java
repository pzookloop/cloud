package com.spc.mail.service.impl;

import com.spc.mail.dto.MailSendResult;
import com.spc.mail.dto.SendMailRequest;
import com.spc.mail.entity.MailMessage;
import com.spc.mail.mapper.MailMessageMapper;
import com.spc.mail.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private static final String PENDING = "PENDING";
    private static final String SENT = "SENT";
    private static final String FAILED = "FAILED";

    private final MailMessageMapper mailMessageMapper;
    private final JavaMailSender mailSender;

    @Value("${mail.sender:${spring.mail.username}}")
    private String sender;

    @Override
    public MailSendResult send(SendMailRequest request) {
        MailMessage message = toEntity(request);
        mailMessageMapper.insert(message);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(sender);
            helper.setTo(request.getTo().toArray(String[]::new));
            setRecipients(helper, request.getCc(), RecipientType.CC);
            setRecipients(helper, request.getBcc(), RecipientType.BCC);
            helper.setSubject(request.getSubject());
            helper.setText(request.getContent(), request.isHtml());
            mailSender.send(mimeMessage);

            mailMessageMapper.markSent(message.getId(), LocalDateTime.now());
            return new MailSendResult(message.getId(), SENT, "邮件已发送");
        } catch (Exception e) {
            mailMessageMapper.markFailed(message.getId(), truncateError(e));
            return new MailSendResult(message.getId(), FAILED, "邮件发送失败：" + e.getMessage());
        }
    }

    private MailMessage toEntity(SendMailRequest request) {
        MailMessage message = new MailMessage();
        message.setRecipients(String.join(",", request.getTo()));
        message.setCcRecipients(join(request.getCc()));
        message.setBccRecipients(join(request.getBcc()));
        message.setSubject(request.getSubject());
        message.setContent(request.getContent());
        message.setHtml(request.isHtml());
        message.setSource(request.getSource());
        message.setStatus(PENDING);
        return message;
    }

    private void setRecipients(MimeMessageHelper helper, List<String> recipients, RecipientType type)
            throws MessagingException {
        if (recipients == null || recipients.isEmpty()) {
            return;
        }
        String[] addresses = recipients.toArray(String[]::new);
        if (type == RecipientType.CC) {
            helper.setCc(addresses);
        } else {
            helper.setBcc(addresses);
        }
    }

    private String join(List<String> addresses) {
        return addresses == null || addresses.isEmpty() ? null : String.join(",", addresses);
    }

    private String truncateError(Exception exception) {
        String message = exception.getMessage() == null ? exception.getClass().getSimpleName() : exception.getMessage();
        return message.substring(0, Math.min(message.length(), 1000));
    }

    private enum RecipientType { CC, BCC }
}
