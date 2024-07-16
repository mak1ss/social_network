package com.practice.social_network.filtering.model.userFollow;

import com.practice.social_network.filtering.FilterableProperty;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.predicate.DateSpecificationBuilder;
import com.practice.social_network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.practice.social_network.model.UserFollow;
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
