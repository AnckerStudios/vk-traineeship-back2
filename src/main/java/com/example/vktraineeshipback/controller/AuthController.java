package com.example.vktraineeshipback.controller;

import com.example.vktraineeshipback.auth.AuthenticateRequest;
import com.example.vktraineeshipback.auth.AuthenticationResponse;
import com.example.vktraineeshipback.auth.RegisterRequest;
import com.example.vktraineeshipback.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/authorization")
    public ResponseEntity<AuthenticationResponse> authorization(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        return ResponseEntity.ok(service.authorization(authHeader));
    }
}
