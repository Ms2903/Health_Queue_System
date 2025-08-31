package com.healthcare.queuesystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host:smtp.gmail.com}")
    private String mailHost;

    @Value("${spring.mail.port:587}")
    private int mailPort;

    @Value("${spring.mail.username:}")
    private String mailUsername;

    @Value("${spring.mail.password:}")
    private String mailPassword;

    @Value("${spring.mail.protocol:smtp}")
    private String mailProtocol;

    @Value("${spring.mail.properties.mail.smtp.auth:true}")
    private String mailAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable:true}")
    private String mailStartTls;

    @Value("${spring.mail.properties.mail.debug:false}")
    private String mailDebug;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        mailSender.setProtocol(mailProtocol);

        Properties mailProperties = mailSender.getJavaMailProperties();
        mailProperties.put("mail.transport.protocol", mailProtocol);
        mailProperties.put("mail.smtp.auth", mailAuth);
        mailProperties.put("mail.smtp.starttls.enable", mailStartTls);
        mailProperties.put("mail.debug", mailDebug);
        mailProperties.put("mail.smtp.ssl.trust", mailHost);
        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        return mailSender;
    }
}