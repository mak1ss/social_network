package com.network.filtering.model.token;

import com.network.filtering.FilterableProperty;
import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.predicate.EqualingSpecificationBuilder;
import com.network.filtering.predicate.IdInEntitySpecificationBuilder;
import com.network.model.Token;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenSpecificationBuilder implements EntityFilterSpecificationBuilder<Token> {

    private final List<FilterableProperty<Token>> filterableProperties = List.of(
            new FilterableProperty<>("token", String.class, new EqualingSpecificationBuilder<>(),
                    EqualingSpecificationBuilder.SUPPORTED_OPERATORS),
            new FilterableProperty<>("user", Integer.class, new IdInEntitySpecificationBuilder<>(),
                    IdInEntitySpecificationBuilder.SUPPORTED_OPERATORS)
    );

    @Override
    public List<FilterableProperty<Token>> getFilterableProperties() {
        return filterableProperties;
    }
}
