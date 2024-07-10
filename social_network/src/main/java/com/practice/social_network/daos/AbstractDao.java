package com.practice.social_network.daos;

import com.practice.social_network.entities.base.Archivable;
import com.practice.social_network.entities.base.PrimaryEntity;
import com.practice.social_network.repositories.PrimaryRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends PrimaryEntity<Integer>> {

    protected abstract PrimaryRepository<Integer, T> getRepository();

    public List<T> getAll() {
        return getRepository().findAll();
    }

    public Optional<T> getById(Integer id) {
        return getRepository().findById(id);
    }

    public T create(T entity) {
        beforeCreate();
        return getRepository().save(entity);
    }

    protected void beforeCreate(){}

    public T update(T entity) {
        beforeUpdate(entity);
        return getRepository().save(entity);
    }

    protected void beforeUpdate(T entity){}

    public T update(T entity, boolean ignorePermissions) {
        if(!ignorePermissions) {
          return update(entity);
        }
        return getRepository().save(entity);
    }

    public void delete(T entity) {
        beforeDelete(entity);
        if(entity instanceof Archivable archivable) {
            archivable.setArchived(true);
            getRepository().save(entity);
        } else {
            delete(entity, true);
        }
    }

    protected void beforeDelete(T entity){}

    public void delete(T entity, boolean ignorePermissions) {
        if(!ignorePermissions) {
            delete(entity);
        } else {
            getRepository().deleteById(entity.getId());
        }
    }

    public void deleteById(Integer id) {
        T entity = getRepository().findById(id).orElseThrow();
        if(entity instanceof Archivable archivable) {
            archivable.setArchived(false);
            getRepository().save(entity);
        } else {
            deleteById(id, true);
        }
    }

    public void deleteById(Integer id, boolean ignorePermissions) {
        if(!ignorePermissions) {
            deleteById(id);
        } else {
            getRepository().deleteById(id);
        }
    }


}
