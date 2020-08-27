package com.example.restservice.controllers;

import com.example.restservice.dto.UserDto;
import com.example.restservice.services.CalculatedUserDto;
import com.example.restservice.services.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {


    private KafkaService kafkaService;

    public UsersController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/users")
    CalculatedUserDto addUser(@RequestBody UserDto user) throws JsonProcessingException {
        return kafkaService.save(user);
    }
}
