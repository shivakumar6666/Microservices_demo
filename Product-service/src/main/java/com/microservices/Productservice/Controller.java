package com.microservices.Productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class Controller {
    @GetMapping(value="/start")
    public String status() {
        return new String("Welcome");
    }
}
