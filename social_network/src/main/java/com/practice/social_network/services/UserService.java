package com.practice.social_network.services;

import com.practice.social_network.daos.UserDao;
import com.practice.social_network.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService extends AbstractService<User> {

    private final UserDao dao;
    private PasswordEncoder passEncoder;

    @Override
    protected UserDao getDao() {
        return dao;
    }

    public Optional<User> findByEmail(String email) {
        return getDao().findByEmail(email);
    }

    public boolean isPasswordsMatch(String password1, String password2) {
        return passEncoder.matches(password1, password2);
    }

    public String encodePassword(String password) {
        return passEncoder.encode(password);
    }

    public void checkEmailAccessibility(String email) {
        if(dao.findByEmail(email) != null) {
            throw new IllegalArgumentException("User with this email address already exists");
        }
    }
}
