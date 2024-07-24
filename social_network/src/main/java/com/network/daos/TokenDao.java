package com.network.daos;

import com.network.filtering.FilteringOperation;
import com.network.filtering.SearchCriteria;
import com.network.filtering.predicate.EqualingSpecification;
import com.network.filtering.predicate.IdInEntitySpecification;
import com.network.model.Token;
import com.network.repositories.PrimaryRepository;
import com.network.repositories.TokenRepository;
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
        SearchCriteria tokenCriteria = new SearchCriteria("refreshToken", FilteringOperation.EQUAL, token);
        EqualingSpecification<Token> tokenSpecification = new EqualingSpecification<>(tokenCriteria);

        return getOne(tokenSpecification);
    }

    public Optional<Token> findByUser(Integer userId) {
        SearchCriteria userIdCriteria = new SearchCriteria("user", FilteringOperation.EQUAL, userId);
        IdInEntitySpecification<Token> tokenSpecification = new IdInEntitySpecification<>(userIdCriteria);

        return getOne(tokenSpecification);
    }
}
