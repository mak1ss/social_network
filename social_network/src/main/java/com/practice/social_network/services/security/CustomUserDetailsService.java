package com.practice.social_network.services.security;

import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findUserByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Email not found");
        }
        return new UserPrincipal(user.get());
    }
}
