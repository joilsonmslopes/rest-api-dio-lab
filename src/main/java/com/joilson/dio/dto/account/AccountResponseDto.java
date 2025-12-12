package com.joilson.dio.dto.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponseDto {
    private Long id;
    private String number;
    private String agency;
    private BigDecimal balance;
    private BigDecimal limit;
}
