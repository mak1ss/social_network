package com.practice.social_network.repositories;

import com.practice.social_network.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseCRUDRepository<User> {

    @Modifying
    @Query("""
            DELETE FROM User u
            WHERE u.fullName = 'test_name'
            """)
    void deleteTestRows();

    Optional<User> findUserByEmail(String email);

}
