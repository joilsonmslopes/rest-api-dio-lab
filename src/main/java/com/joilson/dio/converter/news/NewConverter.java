package com.joilson.dio.converter.news;

import com.joilson.dio.domain.model.New;
import com.joilson.dio.dto.news.NewRequestDto;
import com.joilson.dio.dto.news.NewResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class NewConverter {

    private NewConverter() {}

    public static New toEntity(NewRequestDto dto) {
        if (dto == null) return null;

        New news = new New();
        news.setIcon(dto.getIcon());
        news.setDescription(dto.getDescription());
        return news;
    }

    public static List<New> toEntityList(List<NewRequestDto> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(NewConverter::toEntity)
                .collect(Collectors.toList());
    }

    public static NewResponseDto toResponseDto(New entity) {
        if (entity == null) return null;

        NewResponseDto dto = new NewResponseDto();
        dto.setId(entity.getId());
        dto.setIcon(entity.getIcon());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static List<NewResponseDto> toResponseDtoList(List<New> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(NewConverter::toResponseDto)
                .collect(Collectors.toList());
    }
}
