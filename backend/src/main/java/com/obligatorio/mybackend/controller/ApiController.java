package com.obligatorio.mybackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public ResponseEntity<String> getSong() {
        return ResponseEntity.ok("Hello from Java backend!");
    }
    @GetMapping("/button1")
    public ResponseEntity<String> handleButton1() {
        return ResponseEntity.ok("Response from button 1");
    }

    @GetMapping("/button2")
    public ResponseEntity<String> handleButton2() {
        return ResponseEntity.ok("Response from button 2");
    }

    @GetMapping("/button3")
    public ResponseEntity<String> handleButton3() {
        return ResponseEntity.ok("Response from button 3");
    }

    @GetMapping("/button4")
    public ResponseEntity<String> handleButton4() {
        return ResponseEntity.ok("Response from button 4");
    }

    @GetMapping("/button5")
    public ResponseEntity<String> handleButton5() {
        return ResponseEntity.ok("Response from button 5");
    }
}