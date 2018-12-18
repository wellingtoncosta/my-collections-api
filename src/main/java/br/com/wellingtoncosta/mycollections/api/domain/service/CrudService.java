package br.com.wellingtoncosta.mycollections.api.domain.service;

import java.util.List;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public interface CrudService<T, ID> {

    T save(T model);

    T update(ID id, T model);

    void delete(ID id);

    List<T> findAll();

    T findById(ID id);

}
