package com.krupp.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping("hello")
    String hello(){
        return "hello world";
    }

    @GetMapping("ok")
    String ok(){
        return "真TMD神奇";
    }
}
