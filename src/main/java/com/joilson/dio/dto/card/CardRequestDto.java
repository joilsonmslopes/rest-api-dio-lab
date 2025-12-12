package com.joilson.dio.dto.card;

import lombok.Data;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

@Data
public class CardRequestDto {
    @NotNull(message = "number field is required")
    private String number;

    @NotNull(message = "limit field is required")
    private BigDecimal limit;
}
