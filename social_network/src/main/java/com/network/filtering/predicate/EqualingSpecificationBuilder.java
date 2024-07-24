package com.network.filtering.predicate;

import com.network.filtering.FilteringOperation;
import com.network.filtering.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class EqualingSpecificationBuilder<EntityType> implements SpecificationBuilder<EntityType> {

    public static final List<FilteringOperation> SUPPORTED_OPERATORS = List.of(
            FilteringOperation.EQUAL,
            FilteringOperation.NOT_EQUAL
    );

    @Override
    public Specification<EntityType> build(SearchCriteria searchCriteria) {
        return new EqualingSpecification<>(searchCriteria);
    }
}
