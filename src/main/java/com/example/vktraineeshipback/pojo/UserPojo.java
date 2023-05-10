package com.example.vktraineeshipback.pojo;

import com.example.vktraineeshipback.auth.ERole;
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
public class UserPojo {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String photo;
    public static UserPojo fromEntity(User entity){
        return new UserPojo(entity.getId(),entity.getFirstname(),entity.getLastname(), entity.getEmail(), entity.getPhoto());
    }



//    public User toEntity(UserPojo entity){
//        return new User(entity.getFirstname(),entity.getLastname(), entity.getEmail(), entity.getPhoto());
//    }
}
