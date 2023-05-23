package com.practice.social_network.repositories;

import com.practice.social_network.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Modifying
    @Query("DELETE FROM User u WHERE u.fullName = 'test_name'")
    void deleteTestRows();

    User deleteById(int userId);

    Optional<User> findUserByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.id = :userId")
    void updatePassword(int userId, String newPassword);
}
