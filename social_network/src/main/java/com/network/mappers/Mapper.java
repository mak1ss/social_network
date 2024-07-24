package com.network.mappers;


import com.network.dtos.AbstractRequest;
import com.network.dtos.AbstractResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Mapper<T, ResponseType extends AbstractResponse, RequestType extends AbstractRequest> {

    T requestToEntity(RequestType request, Optional<Integer> id);

    ResponseType entityToResponse(T entity);

    List<ResponseType> entitiesToListResponse(Collection<T> entityList);

}
