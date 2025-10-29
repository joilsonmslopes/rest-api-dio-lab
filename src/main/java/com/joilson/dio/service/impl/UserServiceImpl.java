package com.joilson.dio.service.impl;

import com.joilson.dio.domain.model.User;
import com.joilson.dio.repository.UserRepository;
import com.joilson.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        if (user.getId() != null && userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("User with id " + user.getId() + " already exists");
        }

        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new IllegalArgumentException("User with account number " + user.getAccount().getNumber() + " already exists");
        }
        return userRepository.save(user);
    }
}
