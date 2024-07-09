package com.practice.social_network.controllers;

import lombok.Getter;

@Getter
public enum RelationshipType {
    FOLLOWS("follows"),
    FOLLOWERS("followers");

    private String value;

    RelationshipType(String value) {
        this.value = value;
    }
}
