package com.practice.social_network.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "post_body")
    private String postBody;

    public int getUser(){
        return user.getId();
    }

    public void setUser(User user, boolean isPostSetted){
        this.user = user;
        if(!isPostSetted) {
            user.addPost(this, true);
        }
    }
}
