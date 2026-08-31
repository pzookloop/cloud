CREATE DATABASE IF NOT EXISTS mail_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mail_db;

CREATE TABLE IF NOT EXISTS mail_message (
    id BIGINT NOT NULL AUTO_INCREMENT,
    recipients VARCHAR(4000) NOT NULL,
    cc_recipients VARCHAR(4000) NULL,
    bcc_recipients VARCHAR(4000) NULL,
    subject VARCHAR(255) NOT NULL,
    content LONGTEXT NOT NULL,
    is_html TINYINT(1) NOT NULL DEFAULT 1,
    source VARCHAR(100) NULL,
    status VARCHAR(20) NOT NULL,
    error_message VARCHAR(1000) NULL,
    sent_at DATETIME NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_mail_message_status_created_at (status, created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件发送记录';
