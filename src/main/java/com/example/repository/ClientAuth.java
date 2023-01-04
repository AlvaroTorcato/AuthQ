package com.example.repository;

import com.example.model.JWT;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientAuth {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;
    public List<JWT> send() {
        int n= 0;
        List<JWT> response = (List<JWT>) rabbitTemplate.convertSendAndReceive(directExchange.getName(),"rpc", n);

        System.out.println("Got " + response + "");
        return response;
    }

}
