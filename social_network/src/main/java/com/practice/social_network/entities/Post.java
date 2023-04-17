package com.practice.social_network.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "creation_date")
    @CreatedDate
    private Timestamp creationDate;

    @Column(name = "post_body")
    private String postBody;

    public int getUser() {
        return user.getId();
    }

    public void setUser(User user, boolean isPostSetted) {
        this.user = user;
        if (!isPostSetted) {
            user.addPost(this, true);
        }
    }
}
