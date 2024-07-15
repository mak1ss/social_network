package com.practice.social_network.filtering;

import com.practice.social_network.filtering.predicate.SpecificationBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterableProperty<EntityType> {

    private String propertyName;

    private Class<?> expectedType;

    private SpecificationBuilder<EntityType> specificationBuilder;

    private List<FilteringOperation> supportedOperations;

}
