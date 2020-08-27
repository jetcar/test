package com.example.restservice.services;

import lombok.RequiredArgsConstructor;
import lombok.Value;


@Value
@RequiredArgsConstructor
public class CalculatedUserDto {
    private String firstName;
    private String lastName;
    private int age;
    private double seed;

}
