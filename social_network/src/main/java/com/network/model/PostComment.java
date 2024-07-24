package com.network.model;

import com.network.model.base.Archivable;
import com.network.model.base.Identifiable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts_comments")
public class PostComment implements Identifiable, Archivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "comment_body")
    private String commentBody;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean archived;
}
