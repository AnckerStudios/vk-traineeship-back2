package com.example.vktraineeshipback.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FriendsFK implements Serializable {
    private User user1;
    private User user2;
}
