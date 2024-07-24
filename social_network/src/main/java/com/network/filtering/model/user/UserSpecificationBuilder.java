package com.network.filtering.model.user;

import com.network.filtering.FilterableProperty;
import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.predicate.EqualingSpecificationBuilder;
import com.network.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSpecificationBuilder implements EntityFilterSpecificationBuilder<User> {

    private final List<FilterableProperty<User>> filterableProperties = List.of(
            new FilterableProperty<>("email", String.class, new EqualingSpecificationBuilder<>(),
                    EqualingSpecificationBuilder.SUPPORTED_OPERATORS)
    );

    @Override
    public List<FilterableProperty<User>> getFilterableProperties() {
        return filterableProperties;
    }
}
