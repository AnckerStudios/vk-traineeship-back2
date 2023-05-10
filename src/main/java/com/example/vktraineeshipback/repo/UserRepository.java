package com.example.vktraineeshipback.repo;

import com.example.vktraineeshipback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM _user WHERE id IN (SELECT user1_id FROM friends WHERE user2_id = ?1 AND status = 2) OR id IN (SELECT user2_id FROM friends WHERE user1_id = ?1 AND status = 2)",nativeQuery = true)
    List<User> findMyFriends(UUID id);

    @Query(value = "SELECT * FROM _user WHERE id NOT IN (SELECT user1_id FROM friends WHERE user2_id = ?1) AND id NOT IN (SELECT user2_id FROM friends WHERE user1_id = ?1) AND id != ?1",nativeQuery = true)
    List<User> findOtherPeople(UUID id);
    @Query(value = "SELECT * FROM _user WHERE id IN (SELECT user1_id FROM friends WHERE user2_id = ?1 AND status = 1)",nativeQuery = true)
    List<User> findMyIncomingRequest(UUID id);
    @Query(value = "SELECT * FROM _user WHERE id IN (SELECT user2_id FROM friends WHERE user1_id = ?1 AND status = 1)" ,nativeQuery = true)
    List<User> findMyOutgoingRequest(UUID id);
}
