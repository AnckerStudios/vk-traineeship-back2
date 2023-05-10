package com.example.vktraineeshipback.controller;

import com.example.vktraineeshipback.pojo.UserPojo;
import com.example.vktraineeshipback.service.FriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendsService friendsService;
    @GetMapping("all")
    public ResponseEntity<List<UserPojo>> findMyFriends(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(friendsService.findMyFriends(authHeader));
    }

    @GetMapping("other")
    public ResponseEntity<List<UserPojo>> findOtherPeople(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(friendsService.findOtherPeople(authHeader));
    }

    @GetMapping("incoming")
    public ResponseEntity<List<UserPojo>> findMyIncomingRequest(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(friendsService.findMyIncomingRequest(authHeader));
    }
    @GetMapping("outgoing")
    public ResponseEntity<List<UserPojo>> findMyOutgoingRequest(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(friendsService.findMyOutgoingRequest(authHeader));
    }

    @GetMapping("add/{id}")
    public ResponseEntity<UserPojo> add(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,@PathVariable("id") UUID id ) {
        return ResponseEntity.ok(friendsService.requestFriend(authHeader,id));
    }
    @GetMapping("del/{id}")
    public ResponseEntity del(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,@PathVariable("id") UUID id ) {
        friendsService.delFriend(authHeader,id);
        return ResponseEntity.ok("");
    }
}
