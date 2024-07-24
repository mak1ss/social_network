package com.network.model;

import com.network.model.base.Archivable;
import com.network.model.base.Identifiable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Post implements Identifiable, Archivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "creation_date")
    @CreatedDate
    private LocalDateTime creationDate;

    @Column(name = "post_body")
    private String postBody;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<PostLike> postLikes = new HashSet<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<PostComment> comments = new HashSet<>();

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean archived;
}
