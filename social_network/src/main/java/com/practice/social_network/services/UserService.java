package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.daos.UserDao;
import com.practice.social_network.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService extends AbstractService<User> {

    private final UserDao dao;
    private PasswordEncoder passEncoder;

    @Override
    protected AbstractDao<User> getDao() {
        return dao;
    }

    public boolean isPasswordsMatch(String oldPassword, String newPassword) {
        return passEncoder.matches(oldPassword, newPassword);
    }

    public String encodePassword(String password) {
        return passEncoder.encode(password);
    }
}
