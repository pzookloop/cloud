package com.spc.mail.mapper;

import com.spc.mail.entity.MailMessage;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface MailMessageMapper {
    int insert(MailMessage message);

    int markSent(@Param("id") Long id, @Param("sentAt") LocalDateTime sentAt);

    int markFailed(@Param("id") Long id, @Param("errorMessage") String errorMessage);
}
