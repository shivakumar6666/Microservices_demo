package org.microservices.Authenticationservice.service;

import org.microservices.Authenticationservice.model.UserCredential;
import org.microservices.Authenticationservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String registerUser(UserCredential user){
//        passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userCredentialRepository.save(user);
        return "Registration successful";
    }

    public String genarateToken(String name){
        return jwtService.genarateToken(name);
    }


    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
