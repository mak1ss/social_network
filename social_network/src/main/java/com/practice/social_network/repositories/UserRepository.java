package com.practice.social_network.repositories;

import com.practice.social_network.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Modifying
    void deleteTestRows();

    User deleteById(int userId);
}
