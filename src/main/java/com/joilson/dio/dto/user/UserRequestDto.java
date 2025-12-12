package com.joilson.dio.dto.user;

import com.joilson.dio.domain.model.Account;
import com.joilson.dio.dto.card.CardRequestDto;
import com.joilson.dio.dto.feature.FeatureRequestDto;
import com.joilson.dio.dto.news.NewRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequestDto {
    @NotNull(message = "name field is required")
    private String name;
    @NotNull(message = "account field is required")
    private Account account;
    @NotNull(message = "card field is required")
    private CardRequestDto card;
    @NotNull(message = "features field is required")
    private List<FeatureRequestDto> features = new ArrayList<>();
    @NotNull(message = "news field is required")
    private List<NewRequestDto> news = new ArrayList<>();
}
