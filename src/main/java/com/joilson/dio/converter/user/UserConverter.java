package com.joilson.dio.converter.user;

import com.joilson.dio.domain.model.Card;
import com.joilson.dio.domain.model.Feature;
import com.joilson.dio.domain.model.New;
import com.joilson.dio.domain.model.User;
import com.joilson.dio.dto.card.CardResponseDto;
import com.joilson.dio.dto.feature.FeatureRequestDto;
import com.joilson.dio.dto.feature.FeatureResponseDto;
import com.joilson.dio.dto.news.NewRequestDto;
import com.joilson.dio.dto.news.NewResponseDto;
import com.joilson.dio.dto.user.UserRequestDto;
import com.joilson.dio.dto.user.UserResponseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class UserConverter {

    private UserConverter() {}

    public static User toEntity(UserRequestDto dto) {
        if (dto == null) return null;

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

        User user = new User();
        user.setName(dto.getName());
        user.setAccount(dto.getAccount());
        user.setCard(card);
        user.setFeatures(dto.getFeatures() == null ? new ArrayList<>() : new ArrayList<>(features));
        user.setNews(dto.getNews() == null ? new ArrayList<>() : new ArrayList<>(news));
        return user;
    }

    public static List<User> toEntityList(List<UserRequestDto> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(UserConverter::toEntity)
                .collect(Collectors.toList());
    }


    public static UserResponseDto toResponseDto(User entity) {
        if (entity == null) return null;

        UserResponseDto dto = new UserResponseDto();

        var card = new CardResponseDto();
        var features = new ArrayList<FeatureResponseDto>();
        var news = new ArrayList<NewResponseDto>();

        card.setNumber(dto.getCard().getNumber());
        card.setLimit(dto.getCard().getLimit());

        for (FeatureResponseDto featureRequestDto : dto.getFeatures()) {
            var feature = new FeatureResponseDto();

            feature.setIcon(featureRequestDto.getIcon());
            feature.setDescription(featureRequestDto.getDescription());

            features.add(feature);
        }

        for (NewResponseDto newRequestDto : dto.getNews()) {
            var newItem = new NewResponseDto();
            newItem.setIcon(newRequestDto.getIcon());
            newItem.setDescription(newRequestDto.getDescription());

            news.add(newItem);
        }

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAccount(entity.getAccount());
        dto.setCard(card);
        dto.setFeatures(entity.getFeatures() == null ? new ArrayList<>() : new ArrayList<>(features));
        dto.setNews(entity.getNews() == null ? new ArrayList<>() : new ArrayList<>(news));
        return dto;
    }

    public static List<UserResponseDto> toResponseDtoList(List<User> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(UserConverter::toResponseDto)
                .collect(Collectors.toList());
    }
}
