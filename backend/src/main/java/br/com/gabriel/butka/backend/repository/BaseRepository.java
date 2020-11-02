package br.com.gabriel.butka.backend.repository;

import br.com.gabriel.butka.backend.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends Repository<T, ID> {

    List<T> findAll();

    List<T> findAllById(Iterable<ID> ids);

    Optional<T> findById(ID id);

    T save(T entity);

    void deleteById(ID id);

    void delete(T entity);

}
