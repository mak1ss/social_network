package com.practice.social_network.controllers;

import com.practice.social_network.dtos.AbstractRequest;
import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.model.base.Archivable;
import com.practice.social_network.model.base.Identifiable;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.services.AbstractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@SecurityRequirement(name = "networkScheme")
public abstract class AbstractController<T extends Identifiable & Archivable, RequestType extends AbstractRequest, ResponseType extends AbstractResponse> {

    protected abstract AbstractService<T> getService();

    protected abstract Mapper<T, ResponseType, RequestType> getMapper();

    @Operation(summary = "Get all", description = "Retrieve all entities")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ResponseType>> getAll() {
        List<ResponseType> responseList = getMapper().entitiesToListResponse(getService().getAll());
        return ResponseEntity.ok(responseList);
    }

    @Operation(summary = "Get", description = "Retrieve specific entity by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getRecord(@PathVariable Integer id) {
        Optional<T> entity = getService().getById(id);
        return entity.<ResponseEntity<Object>>map(e -> ResponseEntity.ok(getMapper().entityToResponse(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create", description = "Create new entity")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseType> createRecord(@Valid @RequestBody RequestType request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }

        T entity = getMapper().requestToEntity(request, Optional.empty());
        entity = executeEntityCreate(entity);
        ResponseType response = getMapper().entityToResponse(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update", description = "Update entity")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseType> updateRecord(@PathVariable Integer id, @Valid @RequestBody RequestType request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }

        T entity = getMapper().requestToEntity(request, id.describeConstable());
        entity = executeEntityUpdate(entity);
        ResponseType response = getMapper().entityToResponse(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete", description = "Delete entity")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteRecord(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        executeEntityDelete(id);
        return ResponseEntity.noContent().build();
    }

    protected T executeEntityCreate(T entity) {
        return getService().save(entity);
    }

    protected T executeEntityUpdate(T entity) {
        return getService().update(entity);
    }

    protected void executeEntityDelete(Integer id) {
        getService().deleteById(id);
    }
}
