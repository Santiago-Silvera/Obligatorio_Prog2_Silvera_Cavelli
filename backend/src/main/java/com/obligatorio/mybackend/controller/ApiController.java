package com.obligatorio.mybackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/getSong")
    public ResponseEntity<String> getSong() {
        return ResponseEntity.ok("Hello from Java backend!");
    }   
}