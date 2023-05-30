package com.example.springevent.service;

import com.example.springevent.entity.User;
import com.example.springevent.event.NewUserEvent;
import com.example.springevent.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        NewUserEvent newUserEvent = new NewUserEvent(user);
        publisher.publishEvent(newUserEvent);
    }
}
