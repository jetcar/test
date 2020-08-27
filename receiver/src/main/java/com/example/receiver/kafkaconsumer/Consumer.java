package com.example.receiver.kafkaconsumer;

import com.example.receiver.dto.CalculatedUserDto;
import com.example.receiver.model.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String stringMessage) throws JsonProcessingException {

        CalculatedUserDto message = new ObjectMapper().readValue(stringMessage, CalculatedUserDto.class);

        UserModel user = new UserModel(message.getFirstName() + "_" + message.getLastName(), message.getAge() * message.getSeed());


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        redisTemplate.opsForValue().set(user.getUsername(), json);
        System.out.println(String.format("%s %s has %g score", message.getFirstName(), message.getLastName(), user.getSocialRating()));

    }
}