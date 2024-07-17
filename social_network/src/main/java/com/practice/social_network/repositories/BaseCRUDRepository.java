package com.practice.social_network.repositories;

import com.practice.social_network.model.base.Identifiable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseCRUDRepository<EntityType extends Identifiable> extends PrimaryRepository<Integer, EntityType> {
}
