package com.joilson.dio.service.impl;

import com.joilson.dio.converter.user.UserConverter;
import com.joilson.dio.domain.model.Card;
import com.joilson.dio.domain.model.Feature;
import com.joilson.dio.domain.model.New;
import com.joilson.dio.domain.model.User;
import com.joilson.dio.dto.feature.FeatureRequestDto;
import com.joilson.dio.dto.news.NewRequestDto;
import com.joilson.dio.dto.user.UserRequestDto;
import com.joilson.dio.dto.user.UserResponseDto;
import com.joilson.dio.repository.UserRepository;
import com.joilson.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();

        return UserConverter.toResponseDtoList(users);
    }

    @Override
    public UserResponseDto findById(Long id) {
        var user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);

        return UserConverter.toResponseDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto user) {
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new IllegalArgumentException("User with account number " + user.getAccount().getNumber() + " already exists");
        }

        var userEntity =  UserConverter.toEntity(user);

        var response = userRepository.save(userEntity);

        return UserConverter.toResponseDto(response) ;
    }
    
    @Override
    public UserResponseDto update(Long id, UserRequestDto dto) throws Exception {
        var userFound = userRepository.findById(id).orElseThrow(() -> new Exception("User with id " + id + " not found"));

        var userAccountNumber = dto.getAccount().getNumber();

        if (!userFound.getAccount().getNumber().equals(userAccountNumber) && userRepository.existsByAccountNumber(userAccountNumber)) {
            throw new IllegalArgumentException("User with account number " + userAccountNumber + " already exists");
        }

        var card = new Card();
        var features = new ArrayList<Feature>();
        var news = new ArrayList<New>();

        card.setNumber(dto.getCard().getNumber());
        card.setLimit(dto.getCard().getLimit());

        for (FeatureRequestDto featureRequestDto : dto.getFeatures()) {
            var feature = new Feature();
            feature.setIcon(featureRequestDto.getIcon());
            feature.setDescription(featureRequestDto.getDescription());
            features.add(feature);
        }

        for (NewRequestDto newRequestDto : dto.getNews()) {
            var newItem = new New();
            newItem.setIcon(newRequestDto.getIcon());
            newItem.setDescription(newRequestDto.getDescription());
            news.add(newItem);
        }

        userFound.setAccount(dto.getAccount());
        userFound.setName(dto.getName());
        userFound.setCard(card);
        userFound.setFeatures(features);
        userFound.setNews(news);

        return UserConverter.toResponseDto(userRepository.save(userFound));
    }
}
