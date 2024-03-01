package com.practice.social_network.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(length = 40)
    private String nickname;

    @Column(length = 100)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    //In this case, collection of followings contains the users, followed by this user instance
    @ManyToMany
    @JoinTable(
            name = "followings",
            joinColumns = @JoinColumn(name = "following_user_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_user_id")
    )
    private Set<User> followings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="posts_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="post_id")
    )
    private Set<Post> likedPosts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<PostComment> comments = new HashSet<>();

    public void addPost(Post post, boolean isUserSetted) {
        if (isUserSetted) {
            this.posts.add(post);
        } else {
            post.setUser(this, false);
        }
    }

    public void addFollowing(User newFollowing) {
        this.followings.add(newFollowing);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && fullName.equals(user.fullName) && nickname.equals(user.nickname)
                && email.equals(user.email) && password.equals(user.password) && Objects.equals(posts, user.posts) && Objects.equals(followings, user.followings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, nickname, email, password, posts.size(), followings.size());
    }
}
