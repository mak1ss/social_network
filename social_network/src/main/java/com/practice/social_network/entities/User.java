package com.practice.social_network.entities;

import com.practice.social_network.entities.base.Archivable;
import com.practice.social_network.entities.base.Identifiable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@DynamicUpdate
public class User implements Identifiable, Archivable {
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

    private boolean archived;
}
