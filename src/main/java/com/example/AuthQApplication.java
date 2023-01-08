package com.example;

import com.example.model.JWT;
import com.example.repository.ClientAuth;
import com.example.repository.JWTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AuthQApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthQApplication.class, args);
    }

    @Autowired
    ClientAuth clientAuth;

    @Bean
    public CommandLineRunner loadData(JWTRepository repository) {
        return (args) -> {
            // save a couple of customers
            if(repository.count() == 0){
                List<JWT> lista = clientAuth.send();
                if (lista != null){
                    repository.saveAll(lista);
                }
            }
            //repository.save(new Customer("Jack", "Bauer"));
        };

    }

}
