package com.joilson.dio.converter.card;

import com.joilson.dio.domain.model.Card;
import com.joilson.dio.dto.card.CardRequestDto;
import com.joilson.dio.dto.card.CardResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CardConverter {

    private CardConverter() {}

    public static Card toEntity(CardRequestDto dto) {
        if (dto == null) return null;

        Card card = new Card();
        card.setNumber(dto.getNumber());
        card.setLimit(dto.getLimit());
        return card;
    }

    public static List<Card> toEntityList(List<CardRequestDto> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(CardConverter::toEntity)
                .collect(Collectors.toList());
    }

    public static CardResponseDto toResponseDto(Card entity) {
        if (entity == null) return null;

        CardResponseDto dto = new CardResponseDto();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setLimit(entity.getLimit());
        return dto;
    }

    public static List<CardResponseDto> toResponseDtoList(List<Card> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(CardConverter::toResponseDto)
                .collect(Collectors.toList());
    }
}
