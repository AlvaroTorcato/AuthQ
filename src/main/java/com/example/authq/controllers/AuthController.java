package com.example.authq.controllers;

import com.example.authq.model.UserDetailsDTO;
import com.example.authq.service.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Authentication", description = "Endpoints for authenticate the user")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    JWTService service;


    @Operation(summary = "Get info about JWT token")
    @GetMapping("/search/{jwt}")
    public UserDetailsDTO searchForUserUsingJWT(@PathVariable("jwt") String jwt){
        return service.searchForUser(jwt);
    }

}
