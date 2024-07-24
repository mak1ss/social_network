package com.network.filtering.predicate;

import com.network.filtering.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<EntityType> {

    Specification<EntityType> build(SearchCriteria searchCriteria);
}
