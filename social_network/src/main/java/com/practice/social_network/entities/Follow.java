package com.practice.social_network.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "followings")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "followed_id")
    private User followed;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;
}
