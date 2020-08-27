package com.example.receiver.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class UserModel {

    private String username;

    private double socialRating;
}
