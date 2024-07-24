package com.network.filtering.model.postLike;

import com.network.filtering.FilterableProperty;
import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.predicate.DateSpecificationBuilder;
import com.network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.network.model.PostLike;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
