package com.joilson.dio.service;

import com.joilson.dio.domain.model.User;
import com.joilson.dio.dto.user.UserRequestDto;
import com.joilson.dio.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();

    UserResponseDto findById(Long id);

    UserResponseDto create(UserRequestDto user);

    UserResponseDto update(Long id, UserRequestDto user) throws Exception;
}
