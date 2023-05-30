package com.example.springevent.service;

import com.example.springevent.entity.User;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void sendEmail(User user) {
        Properties props = new Properties() {{
            put("mail.smtp.auth", "true");
            put("mail.smtp.host", "smtp.gmail.com");
            put("mail.transport.protocol", "smtp");
            put("mail.smtp.port", "587");
            put("mail.smtp.starttls.enable", "true");
            put("mail.smtp.ssl.protocols", "TLSv1.2");
        }};

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("khanhquan69@gmail.com", "htusxgiewqgududw");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setSubject("Dang ky thanh cong");
            message.setText("ban da dang ky thanh cong");
            message.setFrom(new InternetAddress("khanhquan69@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException("failed")
        }

    }
}
