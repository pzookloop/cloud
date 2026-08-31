package com.spc.mail.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MailMessage {
    private Long id;
    private String recipients;
    private String ccRecipients;
    private String bccRecipients;
    private String subject;
    private String content;
    private Boolean html;
    private String source;
    private String status;
    private String errorMessage;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
