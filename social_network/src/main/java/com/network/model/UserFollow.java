package com.network.model;

import com.network.model.base.Archivable;
import com.network.model.base.Identifiable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "followings")
public class UserFollow implements Identifiable, Archivable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private User followed;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean archived = false;
}
