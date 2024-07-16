package com.practice.social_network.filtering.model.post;

import com.practice.social_network.filtering.FilterableProperty;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.predicate.DateSpecificationBuilder;
import com.practice.social_network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.practice.social_network.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostSpecificationBuilder implements EntityFilterSpecificationBuilder<Post> {

    private final List<FilterableProperty<Post>> filterableProperties = List.of(
            new FilterableProperty<>("user", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("creationDate", String.class, new DateSpecificationBuilder<>(),
                    DateSpecificationBuilder.SUPPORTED_OPERATORS)
    );

    @Override
    public List<FilterableProperty<Post>> getFilterableProperties() {
        return filterableProperties;
    }
}
