package com.practice.social_network.filtering.model.postLike;

import com.practice.social_network.filtering.FilterableProperty;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.predicate.DateSpecificationBuilder;
import com.practice.social_network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.practice.social_network.model.PostLike;

import java.util.List;

public class PostLikeSpecificationBuilder implements EntityFilterSpecificationBuilder<PostLike> {

    private final List<FilterableProperty<PostLike>> filterableProperties = List.of(
            new FilterableProperty<>("post", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("user", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("likedAt", String.class, new DateSpecificationBuilder<>(),
                    DateSpecificationBuilder.SUPPORTED_OPERATORS)
    );

    @Override
    public List<FilterableProperty<PostLike>> getFilterableProperties() {
        return filterableProperties;
    }
}
