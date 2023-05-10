package com.example.vktraineeshipback.service;

import com.example.vktraineeshipback.auth.AuthenticateRequest;
import com.example.vktraineeshipback.auth.AuthenticationResponse;
import com.example.vktraineeshipback.auth.RegisterRequest;
import com.example.vktraineeshipback.entity.User;
import com.example.vktraineeshipback.repo.UserRepository;
import com.example.vktraineeshipback.auth.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    public AuthenticationResponse register(RegisterRequest request){
        User user = User.builder()
                .id(UUID.randomUUID())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.USER)
                .build();
        User saveUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .firstname(saveUser.getFirstname())
                .lastname(saveUser.getLastname())
                .email(saveUser.getEmail())
                .photo("https://placekitten.com/400/400")
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .photo("https://placekitten.com/400/400")
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authorization(String authHeader) {
        String jwt = authHeader.substring(7);
        String userEmail = jwtService.extractUsername(jwt);
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        System.out.println(userEmail);
        return AuthenticationResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .photo("https://placekitten.com/400/400")
                .token(jwt)
                .build();
    }
}
