package com.example.springevent.event;

import com.example.springevent.entity.User;
import org.springframework.context.ApplicationEvent;

public class NewUserEvent extends ApplicationEvent {
    public NewUserEvent(Object source) {
        super(source);
    }

    public User getUserInfo() {
        return (User) source;
    }
}
