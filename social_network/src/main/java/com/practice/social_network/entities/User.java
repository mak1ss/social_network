package com.practice.social_network.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(length = 40)
    private String nickname;

    @Column(length = 100)
    private String email;

    private String password;

    //In this case, collection of followings contains the users, followed by this user instance
    @ManyToMany
    @JoinTable(
            name = "followings",
            joinColumns = @JoinColumn(name = "following_user_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_user_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> followings = new HashSet<>();

    public void addFollowing(User newFollowing) {
        this.followings.add(newFollowing);
    }

}
