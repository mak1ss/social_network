package com.network.services;

import com.network.daos.UserDao;
import com.network.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService extends AbstractService<User> {

    private final UserDao dao;
    private PasswordEncoder passEncoder;
    private AuthorizationService authService;

    @Override
    protected UserDao getDao() {
        return dao;
    }

    @Override
    protected void beforeUpdate(User entity) {
        if(authService.isOperationAuthorizedOrPerformedByAdmin(entity.getId())) {
            return;
        }

        throw new AccessDeniedException("You do not have permission to update this object");
    }

    @Override
    protected void beforeDelete(User entity) {
        if(authService.isOperationAuthorizedOrPerformedByAdmin(entity.getId())) {
            return;
        }

        throw new AccessDeniedException("You do not have permission to delete this object");
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
        if(dao.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with this email address already exists");
        }
    }
}
