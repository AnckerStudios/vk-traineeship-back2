package com.example.vktraineeshipback.service;

import com.example.vktraineeshipback.entity.Post;
import com.example.vktraineeshipback.entity.User;
import com.example.vktraineeshipback.pojo.PostPojo;
import com.example.vktraineeshipback.pojo.UserPojo;
import com.example.vktraineeshipback.repo.PostRepository;
import com.example.vktraineeshipback.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public List<PostPojo> findMyPosts(String authHeader){
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        return postRepository.findAllByUser(user).stream().map(PostPojo::fromEntity).toList();
    }
    public PostPojo addPost(String text, MultipartFile image, String authHeader){
        String jwt = authHeader.substring(7);
        User user = userRepository.findByEmail(jwtService.extractUsername(jwt)).orElseThrow();
        UUID uuid = UUID.randomUUID();
        String path = "post/"+uuid+".png";
        System.out.println(path);
        File file = new File(Utils.IMAGE_PATH.getPath()+path);
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            out.write(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Post post = new Post(uuid, text, path, 0, user);
        return PostPojo.fromEntity(postRepository.save(post));
    }
}
