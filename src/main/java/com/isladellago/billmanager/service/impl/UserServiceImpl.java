package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.domain.model.User;
import com.isladellago.billmanager.domain.model.UserRepository;
import com.isladellago.billmanager.exception.UserNotFoundException;
import com.isladellago.billmanager.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        log.info("[Get user by email service] email: {}", email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
