package com.network.filtering.model.userFollow;

import com.network.filtering.FilterableProperty;
import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.predicate.DateSpecificationBuilder;
import com.network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.network.model.UserFollow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFollowSpecificationBuilder implements EntityFilterSpecificationBuilder<UserFollow> {

    private final List<FilterableProperty<UserFollow>> filterableProperties = List.of(
            new FilterableProperty<>("follower", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("followed", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("subscriptionDate", String.class, new DateSpecificationBuilder<>(),
                    DateSpecificationBuilder.SUPPORTED_OPERATORS)
    );

    @Override
    public List<FilterableProperty<UserFollow>> getFilterableProperties() {
        return filterableProperties;
    }
}
