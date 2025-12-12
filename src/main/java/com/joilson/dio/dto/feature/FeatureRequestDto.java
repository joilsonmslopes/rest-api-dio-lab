package com.joilson.dio.dto.feature;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class FeatureRequestDto {
    @NotNull(message = "icon field is required")
    private String icon;

    @NotNull(message = "description field is required")
    private String description;
}
