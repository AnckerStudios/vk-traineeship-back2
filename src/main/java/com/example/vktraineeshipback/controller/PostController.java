package com.example.vktraineeshipback.controller;

import com.example.vktraineeshipback.pojo.PostPojo;
import com.example.vktraineeshipback.pojo.UserPojo;
import com.example.vktraineeshipback.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostPojo> savePost(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestPart("post") String post, @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok(postService.addPost(post, image,authHeader));
    }
    @GetMapping("myPosts")
    public ResponseEntity<List<PostPojo>> findMyPosts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        return ResponseEntity.ok(postService.findMyPosts(authHeader));
    }
}
