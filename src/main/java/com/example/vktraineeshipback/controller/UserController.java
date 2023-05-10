package com.example.vktraineeshipback.controller;

import com.example.vktraineeshipback.pojo.UserPojo;
import com.example.vktraineeshipback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    @GetMapping("1")
    public int test(){
        System.out.println("saaaaaaaaaa");
        return 1;
    }
    private final UserService userService;
    @GetMapping("all")
    public ResponseEntity<List<UserPojo>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }



    @GetMapping("findByUsername/{username}")
    public ResponseEntity<UserPojo> findByUserName(@PathVariable("username") String username ) {
        return ResponseEntity.ok(userService.findByUserName(username));
    }
}
