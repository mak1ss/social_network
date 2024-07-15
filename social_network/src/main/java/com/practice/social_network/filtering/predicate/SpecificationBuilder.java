package com.practice.social_network.filtering.predicate;

import com.practice.social_network.filtering.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<EntityType> {

    Specification<EntityType> build(SearchCriteria searchCriteria);
}
