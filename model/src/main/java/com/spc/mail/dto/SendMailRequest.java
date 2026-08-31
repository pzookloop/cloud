package com.spc.mail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SendMailRequest {

    @NotEmpty(message = "收件人不能为空")
    @Size(max = 50, message = "收件人数量不能超过 50")
    private List<@Email(message = "收件人邮箱格式不正确") String> to;

    @Size(max = 50, message = "抄送人数量不能超过 50")
    private List<@Email(message = "抄送人邮箱格式不正确") String> cc;

    @Size(max = 50, message = "密送人数量不能超过 50")
    private List<@Email(message = "密送人邮箱格式不正确") String> bcc;

    @NotBlank(message = "邮件主题不能为空")
    @Size(max = 255, message = "邮件主题不能超过 255 个字符")
    private String subject;

    @NotBlank(message = "邮件内容不能为空")
    private String content;

    /** true 表示 content 是 HTML，false 表示纯文本。 */
    private boolean html = true;

    /** 调用方服务名或业务标识，便于审计和排查。 */
    @Size(max = 100, message = "来源不能超过 100 个字符")
    private String source;
}
