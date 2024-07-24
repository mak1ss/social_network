package com.network.filtering.model.post;

import com.network.filtering.FilterableProperty;
import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.predicate.DateSpecificationBuilder;
import com.network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.network.model.Post;
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
