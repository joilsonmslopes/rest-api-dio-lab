package com.joilson.dio.dto.card;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardResponseDto {
    private Long id;
    private String number;
    private BigDecimal limit;
}
