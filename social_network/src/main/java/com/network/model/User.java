package com.network.model;

import com.network.model.base.Archivable;
import com.network.model.base.Identifiable;
import com.network.model.base.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

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

    public boolean isAdmin() {
        return Objects.equals(role, Role.ROLE_ADMIN);
    }
}
