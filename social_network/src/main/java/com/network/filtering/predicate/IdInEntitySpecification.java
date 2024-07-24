package com.network.filtering.predicate;

import com.network.filtering.FilteringOperation;
import com.network.filtering.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class IdInEntitySpecification<EntityType> implements Specification<EntityType> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<EntityType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(criteria.getOperation().equals(FilteringOperation.EQUAL)) {
            return cb.equal(root.get(criteria.getKey()).get("id"), criteria.getValue());
        }

        return null;
    }
}
