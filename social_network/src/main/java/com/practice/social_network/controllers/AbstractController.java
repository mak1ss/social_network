package com.practice.social_network.controllers;

import com.practice.social_network.dtos.AbstractRequest;
import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.entities.base.Archivable;
import com.practice.social_network.entities.base.Identifiable;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.services.AbstractService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractController<T extends Identifiable & Archivable, RequestType extends AbstractRequest, ResponseType extends AbstractResponse> {

    protected abstract AbstractService<T> getService();

    protected abstract Mapper<T, ResponseType, RequestType> getMapper();

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ResponseType>> getAll() {
        List<ResponseType> responseList = getMapper().entitiesToListResponse(getService().getAll());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getRecord(@PathVariable Integer id) {
        Optional<T> entity = getService().getById(id);
        return entity.<ResponseEntity<Object>>map(e -> ResponseEntity.ok(getMapper().entityToResponse(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseType> createRecord(@Valid @RequestBody RequestType request) {
        if(request == null) {
            return ResponseEntity.badRequest().build();
        }

        T entity = getMapper().requestToEntity(request);
        entity = executeEntityCreate(entity);
        ResponseType response = getMapper().entityToResponse(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseType> updateRecord(@Valid @RequestBody RequestType request) {
        if(request == null) {
            return ResponseEntity.badRequest().build();
        }

        T entity = getMapper().requestToEntity(request);
        entity = executeEntityUpdate(entity);
        ResponseType response = getMapper().entityToResponse(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteRecord(@PathVariable Integer id) {
        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected T executeEntityCreate(T entity) {
        return getService().save(entity);
    }

    protected T executeEntityUpdate(T entity) {
        return getService().update(entity);
    }
}
