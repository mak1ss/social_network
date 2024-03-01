package com.practice.social_network.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "creation_date")
    @CreatedDate
    private Timestamp creationDate;

    @Column(name = "post_body")
    private String postBody;

    @ManyToMany(mappedBy = "likedPosts")
    @Lazy
    private Set<User> likes = new HashSet<>();

    @OneToMany(mappedBy = "post")
    @Lazy
    Set<PostComment> comments = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user, boolean isPostSetted) {
        this.user = user;
        if (!isPostSetted) {
            user.addPost(this, true);
        }
    }
}
