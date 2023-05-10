package com.example.vktraineeshipback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(FriendsFK.class)
public class Friends {
    @Id
    @ManyToOne
    @MapsId("user1Id")
    @JoinColumn(name = "user1_id")
    private User user1;

    @Id
    @ManyToOne
    @MapsId("user2Id")
    @JoinColumn(name = "user2_id",foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user2;

    private int status;
}
