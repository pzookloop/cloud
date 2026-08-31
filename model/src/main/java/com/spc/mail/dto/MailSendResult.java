package com.spc.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailSendResult {
    private Long mailId;
    private String status;
    private String message;
}
