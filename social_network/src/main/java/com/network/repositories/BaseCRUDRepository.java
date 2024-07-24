package com.network.repositories;

import com.network.model.base.Identifiable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseCRUDRepository<EntityType extends Identifiable> extends PrimaryRepository<Integer, EntityType> {
}
