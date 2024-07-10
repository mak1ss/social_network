package com.practice.social_network.entities.base;

public interface PrimaryEntity<T>{

    T getId();
    void setId(T id);
}
