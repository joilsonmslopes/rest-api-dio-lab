package com.joilson.dio.converter.feature;

import com.joilson.dio.domain.model.Feature;
import com.joilson.dio.dto.feature.FeatureRequestDto;
import com.joilson.dio.dto.feature.FeatureResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class FeatureConverter {

    private FeatureConverter() {}

    public static Feature toEntity(FeatureRequestDto dto) {
        if (dto == null) return null;

        Feature feature = new Feature();
        feature.setIcon(dto.getIcon());
        feature.setDescription(dto.getDescription());
        return feature;
    }

    public static List<Feature> toEntityList(List<FeatureRequestDto> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(FeatureConverter::toEntity)
                .collect(Collectors.toList());
    }

    public static FeatureResponseDto toResponseDto(Feature entity) {
        if (entity == null) return null;

        FeatureResponseDto dto = new FeatureResponseDto();
        dto.setId(entity.getId());
        dto.setIcon(entity.getIcon());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static List<FeatureResponseDto> toResponseDtoList(List<Feature> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(FeatureConverter::toResponseDto)
                .collect(Collectors.toList());
    }
}
