package com.practice.social_network.filtering;

import lombok.Data;

@Data
public class SearchCriteria {

    private String key;

    private FilteringOperation operation;

    private Object value;
}
