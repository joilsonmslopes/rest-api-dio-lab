package com.joilson.dio.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple error response wrapper that carries a map of field -> error message.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorResponse {
    private Map<String, String> errors;

    public void addErrors(String code, String message) {
        if (errors != null) {
            this.errors.put(code, message);
        }

        this.errors = new HashMap<>();
    }
}
