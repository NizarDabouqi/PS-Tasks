package com.progressoft.intern.SpringApi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
public class RandomDataController {


    @GetMapping("/randomInt")
    public String getRandomInteger(){
        SecureRandom secureRandom = new SecureRandom();
        return String.valueOf(secureRandom.nextInt(100));
    }

}
