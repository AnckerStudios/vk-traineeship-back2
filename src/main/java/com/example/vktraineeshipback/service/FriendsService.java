package com.example.vktraineeshipback.service;

import com.example.vktraineeshipback.entity.Friends;
import com.example.vktraineeshipback.entity.FriendsFK;
import com.example.vktraineeshipback.entity.User;
import com.example.vktraineeshipback.pojo.UserPojo;
import com.example.vktraineeshipback.repo.FriendsRepository;
import com.example.vktraineeshipback.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendsService {
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    private final JwtService jwtService;

    public List<UserPojo> findMyFriends(String authHeader){
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        System.out.println(user.getEmail());
        List<User> list = userRepository.findMyFriends(user.getId());
        return list.stream().map(UserPojo::fromEntity).toList();
    }

    public List<UserPojo> findOtherPeople(String authHeader){
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        List<User> list = userRepository.findOtherPeople(user.getId());
        return list.stream().map(UserPojo::fromEntity).toList();
    }

    public List<UserPojo> findMyIncomingRequest(String authHeader){
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        List<User> list = userRepository.findMyIncomingRequest(user.getId());
        return list.stream().map(UserPojo::fromEntity).toList();
    }
    public List<UserPojo> findMyOutgoingRequest(String authHeader){
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        List<User> list = userRepository.findMyOutgoingRequest(user.getId());
        return list.stream().map(UserPojo::fromEntity).toList();
    }

    public UserPojo requestFriend(String authHeader, UUID userId){
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        User user2 = userRepository.findById(userId).orElseThrow();
        Friends friends = new Friends(user2, user, 1);
        Friends resFriends = friendsRepository.save(friends);
        return UserPojo.fromEntity(user2);
    }

    public void delFriend(String authHeader, UUID id) {
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        friendsRepository.delFriend(user.getId(), id);
    }
}
