package com.practice.social_network.model;

import com.practice.social_network.model.base.Archivable;
import com.practice.social_network.model.base.Identifiable;
import com.practice.social_network.model.base.Role;
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

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(length = 40, unique = true, nullable = false)
    private String nickname;

    @Column(length = 100, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean archived;
}
