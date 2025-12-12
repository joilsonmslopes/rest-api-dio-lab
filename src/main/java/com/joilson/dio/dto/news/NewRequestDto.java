package com.joilson.dio.dto.news;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class NewRequestDto {
    @NotNull(message = "icon field is required")
    private String icon;

    @NotNull(message = "description field is required")
    private String description;
}
