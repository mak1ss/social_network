package com.network.filtering.model.postComment;

import com.network.filtering.FilterableProperty;
import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.predicate.DateSpecificationBuilder;
import com.network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.network.model.PostComment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
