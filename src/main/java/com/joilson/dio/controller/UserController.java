package com.joilson.dio.controller;

import com.joilson.dio.controller.exception.ErrorResponse;
import com.joilson.dio.converter.user.UserConverter;
import com.joilson.dio.domain.model.User;
import com.joilson.dio.dto.user.UserRequestDto;
import com.joilson.dio.dto.user.UserResponseDto;
import com.joilson.dio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            var user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            var errors = new ErrorResponse();

            errors.addErrors("UNKNOWN", e.getMessage());

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserRequestDto dto) {
        try {
            var userCreated = userService.create(dto);
            var location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userCreated.getId())
                    .toUri();
            return ResponseEntity.created(location).body(userCreated);
        } catch (Exception e) {
            var errors = new ErrorResponse();

            errors.addErrors("UNKNOWN", e.getMessage());

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody @Valid UserRequestDto dto) {
        try {
            var userUpdated = userService.update(id, dto);
            return ResponseEntity.ok(userUpdated);
        } catch (Exception e) {
            var errors = new HashMap<>();

            errors.put("UNKNOWN", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
    }
}
