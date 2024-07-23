package com.practice.social_network.daos;

import com.practice.social_network.filtering.FilteringOperation;
import com.practice.social_network.filtering.SearchCriteria;
import com.practice.social_network.filtering.predicate.EqualingSpecification;
import com.practice.social_network.filtering.predicate.IdInEntitySpecification;
import com.practice.social_network.model.Token;
import com.practice.social_network.repositories.PrimaryRepository;
import com.practice.social_network.repositories.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenDao extends AbstractDao<Token> {

    private TokenRepository repository;

    @Override
    protected PrimaryRepository<Integer, Token> getRepository() {
        return repository;
    }

    public Optional<Token> findByToken(String token) {
        SearchCriteria tokenCriteria = new SearchCriteria("token", FilteringOperation.EQUAL, token);
        EqualingSpecification<Token> tokenSpecification = new EqualingSpecification<>(tokenCriteria);

        return getOne(tokenSpecification);
    }

    public Optional<Token> findByUser(Integer userId) {
        SearchCriteria userIdCriteria = new SearchCriteria("user", FilteringOperation.EQUAL, userId);
        IdInEntitySpecification<Token> tokenSpecification = new IdInEntitySpecification<>(userIdCriteria);

        return getOne(tokenSpecification);
    }
}
