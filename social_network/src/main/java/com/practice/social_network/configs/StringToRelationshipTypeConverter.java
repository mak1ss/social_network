package com.practice.social_network.configs;

import com.practice.social_network.controllers.RelationshipType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StringToRelationshipTypeConverter implements Converter<String, RelationshipType> {

    @Override
    public RelationshipType convert(String source) {
        return Arrays.stream(RelationshipType.values())
                .filter(type -> type.getValue().equals(source))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid relationship type: " + source)
        );
    }
}
