package com.network.filtering.predicate;

import com.network.filtering.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class EqualingSpecification<EntityType> implements Specification<EntityType> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<EntityType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        switch(criteria.getOperation()) {
            case EQUAL -> {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            case NOT_EQUAL -> {
                return criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            }
        }

        return criteriaBuilder.conjunction();
    }
}
