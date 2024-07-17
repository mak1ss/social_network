package com.practice.social_network.filtering.predicate;

import com.practice.social_network.filtering.SearchCriteria;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class EqualOrContainSpecification<EntityType> implements Specification<EntityType> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<EntityType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        switch (criteria.getOperation()) {
            case EQUAL: {
                return cb.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            case CONTAIN: {
                var lowerCaseKey = cb.lower(root.get(criteria.getKey()));
                String lowerCaseValue = ((String) criteria.getValue()).toLowerCase();
                return cb.like(lowerCaseKey, "%" + lowerCaseValue + "%");
            }
        }

        return cb.conjunction();
    }
}
