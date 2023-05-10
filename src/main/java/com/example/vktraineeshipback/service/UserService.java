package com.example.vktraineeshipback.service;

import com.example.vktraineeshipback.entity.User;
import com.example.vktraineeshipback.pojo.UserPojo;
import com.example.vktraineeshipback.repo.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public List<UserPojo> findAll(){
        return userRepository.findAll().stream().map(UserPojo::fromEntity).toList();
    }
    public User getByLogin(@NonNull String login) {
        return userRepository.findByEmail(login).orElseThrow();
    }
    public User findById(UUID id) {
        var user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    public UserPojo findByUserName(String userName) {
        var user = userRepository.findByEmail(userName);
        if(user.isPresent()){
            return UserPojo.fromEntity(user.get());
        }
        return null;
    }

}
