package com.adn.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;

@RestController
public class MutanteController {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(){
        return "hola";
    }
}
