package com.practice.social_network.model.base;

public interface PrimaryEntity<T>{

    T getId();
    void setId(T id);
}
