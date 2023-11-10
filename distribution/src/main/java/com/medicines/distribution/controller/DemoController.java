package com.medicines.distribution.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/demo")
public class DemoController {

    @GetMapping
    public String getResponse() {
        return "Hello";
    }
}
