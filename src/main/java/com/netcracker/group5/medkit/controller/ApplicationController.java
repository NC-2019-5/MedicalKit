package com.netcracker.group5.medkit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping("/")
    pubing hello() {
        return "Hello, world !";
    }
}
