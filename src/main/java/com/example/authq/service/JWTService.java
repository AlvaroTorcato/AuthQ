package com.example.authq.service;

import com.example.authq.model.JWT;
import com.example.authq.model.LoginRequest;
import com.example.authq.model.UserDTO;
import com.example.authq.model.UserDetailsDTO;
import com.example.authq.repository.JWTRepository;
import com.example.authq.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTService {
    @Value("${sidis.secret}")
    private String secret;

    @Value("${sidis.password}")
    private String passwordSecret;

    @Autowired
    private JWTRepository jwtRepository;

    @Autowired
    private UserRepository userRepository;
    public String createJWT(LoginRequest loginRequest){
        String string = generate(loginRequest);
        JWT jwt = new JWT(string);
        jwtRepository.save(jwt);
        return string;
    }

    public String generate(LoginRequest loginRequest){
        UserDTO user = userRepository.findUserDTO(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found ");
        }
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("username", loginRequest.getUsername())
                .claim("id", user.getId())
                .claim("roles", user.getRoles().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30l, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();
        return jwtToken;
    }

    public UserDetailsDTO searchForUser(String jwt) {
        JWT find = jwtRepository.search(jwt);
        if (find == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "JWT Not Found ");
        }
        Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(jwt).getBody();
        UserDetailsDTO user = new UserDetailsDTO(
                claims.get("id",Integer.class),
                claims.get("username", String.class),
                claims.get("roles",String.class));
        Date expTime = claims.get("exp", Date.class);
        Instant now = Instant.now();
        Date date = Date.from(now);
        if (expTime.after(date)){
           new ResponseStatusException(HttpStatus.FORBIDDEN, "Expired token ");
        }
        return user;

    }
}