package com.practice.social_network.repositories;

import com.practice.social_network.model.base.PrimaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface PrimaryRepository<IdType, EntityType extends PrimaryEntity<IdType>> extends JpaRepository<EntityType, IdType>,
        PagingAndSortingRepository<EntityType, IdType>, JpaSpecificationExecutor<EntityType> {

}
