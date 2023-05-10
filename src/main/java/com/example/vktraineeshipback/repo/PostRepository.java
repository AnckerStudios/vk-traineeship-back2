package com.example.vktraineeshipback.repo;

import com.example.vktraineeshipback.entity.Post;
import com.example.vktraineeshipback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByUser(User user);
}
