package org.microservices.Authenticationservice.controller;

import org.microservices.Authenticationservice.model.UserCredential;
import org.microservices.Authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth")
public class AuthController {


    @Autowired
    private UserService userService;


    @PostMapping(value ="/register")
    public String addNewUser(@RequestBody UserCredential credential) {

        return userService.registerUser(credential);
    }

    @PostMapping(value ="/token")
    public String getToken(@RequestBody UserCredential credential) {

        return userService.genarateToken(credential.getName());
    }


    @GetMapping(value ="/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }



}
