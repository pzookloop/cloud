# Mail service

`service-mail` 是统一的业务邮件服务，服务名为 `service-mail`、默认端口为 `20010`。

## 初始化与配置

执行 [mail-schema.sql](src/main/resources/db/mail-schema.sql) 创建 `mail_db` 和邮件记录表。启动前配置下列环境变量；密码不要提交到配置文件。

```text
MAIL_DB_URL=jdbc:mysql://127.0.0.1:3306/mail_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
MAIL_DB_USERNAME=root
MAIL_DB_PASSWORD=your-db-password
MAIL_SMTP_HOST=smtp.example.com
MAIL_SMTP_PORT=587
MAIL_SMTP_USERNAME=no-reply@example.com
MAIL_SMTP_PASSWORD=your-smtp-password
MAIL_SENDER=no-reply@example.com
```

## 其他服务通过 OpenFeign 调用

调用方依赖 `mail` 模块后，启用共享客户端：

```java
@EnableFeignClients(clients = MailFeignClient.class)
@SpringBootApplication
class OtherApplication {}
```

随后注入 `MailFeignClient` 并调用 `send`：

```java
MailSendResult result = mailFeignClient.send(request);
```

接口为 `POST /mail/send`。服务会先写入 `mail_message`，初始状态为 `PENDING`；SMTP 成功后置为 `SENT`，失败则置为 `FAILED` 并记录错误摘要。
