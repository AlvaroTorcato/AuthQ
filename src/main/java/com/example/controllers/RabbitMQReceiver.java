package com.example.controllers;

import com.example.model.JWT;
import com.example.repository.JWTRepository;
import com.example.service.JWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQReceiver {
    @Autowired
    private JWTService jwtService;
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    @Autowired
    JWTRepository repository;
    @RabbitListener(queues= "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String jwt)throws JsonProcessingException{
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + jwt);

        ObjectMapper objectMapper = new ObjectMapper();
        JWT obj=objectMapper.readValue(jwt, JWT.class);


        //jwtService.createJWT(obj);

       // repository.save(obj);
        jwtService.createJWT(obj);
    }

   /* @Autowired
    JWTRepository repository;
    private static Logger logger = LogManager.getLogger(RabbitMQReceiver.class.toString());
    @RabbitHandler
    public void receiver(JWT jwt) {
        repository.save(jwt);
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + jwt.toString());
    }*/
}
