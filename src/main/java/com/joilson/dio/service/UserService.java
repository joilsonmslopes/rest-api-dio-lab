package com.joilson.dio.service;

import com.joilson.dio.domain.model.User;

public interface UserService {
    User findById(Long id);

    User create(User user);
}
