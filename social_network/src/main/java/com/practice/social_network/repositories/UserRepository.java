package com.practice.social_network.repositories;

import com.practice.social_network.model.User;

import java.util.Optional;

public interface UserRepository extends BaseCRUDRepository<User> {

    Optional<User> findUserByEmail(String email);

}
