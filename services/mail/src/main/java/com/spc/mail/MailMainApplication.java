package com.spc.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spc.mail.mapper")
public class MailMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailMainApplication.class, args);
    }
}
