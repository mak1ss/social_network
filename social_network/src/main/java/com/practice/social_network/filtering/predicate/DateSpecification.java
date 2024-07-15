package com.practice.social_network.filtering.predicate;

import com.practice.social_network.filtering.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@AllArgsConstructor
public class DateSpecification<EntityType> implements Specification<EntityType> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<EntityType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        LocalDateTime criteriaDate = (LocalDateTime) criteria.getValue();
        switch(criteria.getOperation()) {
            case EQUAL -> {
                return cb.equal(root.get(criteria.getKey()), criteriaDate);
            }
            case NOT_EQUAL -> {
                return cb.notEqual(root.get(criteria.getKey()), criteriaDate);
            }
            case GREATER_THEN -> {
                return cb.greaterThan(root.get(criteria.getKey()), criteriaDate);
            }
            case GREATER_OR_EQUAL -> {
                return cb.greaterThanOrEqualTo(root.get(criteria.getKey()), criteriaDate);
            }
            case LESS_THEN -> {
                return cb.lessThan(root.get(criteria.getKey()), criteriaDate);
            }
            case LESS_OR_EQUAL -> {
                return cb.lessThanOrEqualTo(root.get(criteria.getKey()), criteriaDate);
            }
        }

        return cb.conjunction();
    }
}
