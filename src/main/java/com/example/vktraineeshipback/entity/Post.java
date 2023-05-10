package com.example.vktraineeshipback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    private UUID id;
    private String text;
    private String photo;

    private int likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
