package com.practice.social_network.repositories;

import com.practice.social_network.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer>, JpaRepository<User, Integer> {

    @Modifying
    @Query("""
            DELETE FROM User u
            WHERE u.fullName = 'test_name'
            """)
    void deleteTestRows();

    Optional<User> findUserByEmail(String email);

}
