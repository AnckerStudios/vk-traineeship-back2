package com.example.vktraineeshipback.repo;

import com.example.vktraineeshipback.entity.Friends;
import com.example.vktraineeshipback.entity.FriendsFK;
import com.example.vktraineeshipback.entity.Post;
import com.example.vktraineeshipback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, FriendsFK> {
    @Query(value = "SELECT * FROM _user WHERE id = (SELECT user1_id FROM friends WHERE user2_id = ?1 AND status = 2) OR id = (SELECT user2_id FROM friends WHERE user1_id = ?1 AND status = 2)",nativeQuery = true)
    List<User> findMyFriends(UUID id);

    @Query(value = "DELETE FROM friends WHERE (user1_id = ?1 AND user2_id = ?2) OR (user1_id = ?2 AND user2_id = ?1)",nativeQuery = true)
    void delFriend(UUID id1, UUID id2);
}
