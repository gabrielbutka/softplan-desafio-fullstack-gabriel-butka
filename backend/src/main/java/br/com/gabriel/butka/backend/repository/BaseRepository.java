package br.com.gabriel.butka.backend.repository;

import br.com.gabriel.butka.backend.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends Repository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    void deleteById(ID id);

    void delete(T entity);

}
