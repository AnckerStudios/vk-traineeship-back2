package com.example.vktraineeshipback.pojo;

import com.example.vktraineeshipback.entity.Post;
import com.example.vktraineeshipback.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPojo {
    private UUID id;
    private String text;
    private String photo;
    private int likes;
    private UserPojo user;


    public static PostPojo fromEntity(Post entity){
        return new PostPojo(entity.getId(),entity.getText(), entity.getPhoto(), entity.getLikes(), UserPojo.fromEntity(entity.getUser()));
    }
}
