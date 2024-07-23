package com.practice.social_network.daos;

import com.practice.social_network.filtering.FilteringOperation;
import com.practice.social_network.filtering.SearchCriteria;
import com.practice.social_network.filtering.predicate.EqualingSpecification;
import com.practice.social_network.model.User;
import com.practice.social_network.repositories.PrimaryRepository;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDao extends AbstractDao<User> {

    private final UserRepository repository;

    @Override
    protected PrimaryRepository<Integer, User> getRepository() {
        return repository;
    }

    public Optional<User> findByEmail(String email) {
        SearchCriteria criteria = new SearchCriteria("email", FilteringOperation.EQUAL, email);
        EqualingSpecification<User> specification = new EqualingSpecification<>(criteria);

        return getOne(specification);
    }
}
