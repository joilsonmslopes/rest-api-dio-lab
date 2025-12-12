package com.joilson.dio.dto.user;

import com.joilson.dio.domain.model.Account;
import com.joilson.dio.domain.model.Card;
import com.joilson.dio.domain.model.Feature;
import com.joilson.dio.domain.model.New;
import com.joilson.dio.dto.card.CardResponseDto;
import com.joilson.dio.dto.feature.FeatureResponseDto;
import com.joilson.dio.dto.news.NewResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private Account account;
    private CardResponseDto card;
    private List<FeatureResponseDto> features = new ArrayList<>();
    private List<NewResponseDto> news = new ArrayList<>();
}
