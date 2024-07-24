package com.network.daos;

import com.network.filtering.FilteringOperation;
import com.network.filtering.SearchCriteria;
import com.network.filtering.predicate.EqualingSpecification;
import com.network.model.User;
import com.network.repositories.PrimaryRepository;
import com.network.repositories.UserRepository;
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
