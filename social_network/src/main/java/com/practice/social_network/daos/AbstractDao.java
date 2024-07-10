package com.practice.social_network.daos;

import com.practice.social_network.entities.base.PrimaryEntity;
import com.practice.social_network.repositories.PrimaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class AbstractDao<T extends PrimaryEntity<Integer>> {

    protected abstract PrimaryRepository<Integer, T> getRepository();

    public Page<T> get(Pageable pageable) {
        return get(pageable, false);
    }

    public Optional<T> getById(Integer id) {
        return getRepository().findById(id);
    }


}
