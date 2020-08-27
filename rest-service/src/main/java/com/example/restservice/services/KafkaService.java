package com.example.restservice.services;

import com.example.restservice.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaService {

    @Value("${seed}")
    private double seed;


    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "users";
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CalculatedUserDto save(UserDto userDto) throws JsonProcessingException {


        var calculatedUserDto = new CalculatedUserDto(userDto.getFirstName(),userDto.getLastName(),userDto.getAge(), seed);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString( calculatedUserDto );
        this.kafkaTemplate.send(TOPIC, json);

        return calculatedUserDto;
    }
}
