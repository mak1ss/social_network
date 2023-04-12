package com.practice.social_network.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@NamedNativeQuery(name = "Post.getFriendsPosts", resultClass = Post.class, query = "SELECT * FROM posts WHERE posts.user_id IN " +
        "(SELECT followed_user_id from followings WHERE followings.following_user_id = ?1)")
@NamedQuery(name = "Post.updatePost" , query = "update Post as post SET post.postBody = ?1 WHERE post.id = ?2 AND post.user.id = ?3")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
