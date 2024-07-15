package com.practice.social_network.filtering.model.postComment;

import com.practice.social_network.filtering.FilterableProperty;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.predicate.DateSpecificationBuilder;
import com.practice.social_network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.practice.social_network.model.PostComment;

import java.util.Date;
import java.util.List;

public class PostCommentSpecificationBuilder implements EntityFilterSpecificationBuilder<PostComment> {

    private final List<FilterableProperty<PostComment>> filterableProperties = List.of(
            new FilterableProperty<>("post", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("creationDate", String.class, new DateSpecificationBuilder<>(),
                    DateSpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("user", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS)
    );

    @Override
    public List<FilterableProperty<PostComment>> getFilterableProperties() {
        return filterableProperties;
    }
}
