package br.com.gabriel.butka.backend.service;

import br.com.gabriel.butka.backend.entity.BaseEntity;
import br.com.gabriel.butka.backend.repository.BaseRepository;

import java.util.List;

public abstract class BaseService<T extends BaseEntity, R extends BaseRepository<T, Long>> {

    protected abstract R getRepository();

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public T findById(Long id) {
        return getRepository().findById(id);
    }

    public T save(T entity) {
        return getRepository().save(entity);
    }

    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    public void delete(T entity) {
        getRepository().delete(entity);
    }

}
