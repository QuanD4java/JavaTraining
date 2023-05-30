package com.example.springevent.event.listener;

import com.example.springevent.entity.User;
import com.example.springevent.event.NewUserEvent;
import com.example.springevent.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserEventListener {
    private final MailService mailService;

    @EventListener
    public void sendEmail(NewUserEvent event) {
        User user = event.getUserInfo();
        mailService.sendEmail(user);
        System.out.println("event chay");
    }
}
