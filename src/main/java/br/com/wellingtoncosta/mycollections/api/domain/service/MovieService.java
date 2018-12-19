package br.com.wellingtoncosta.mycollections.api.domain.service;

import br.com.wellingtoncosta.mycollections.api.domain.model.Movie;

import java.util.List;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public interface MovieService extends CrudService<Movie, Long> {

    List<Movie> findAllByOwner(Long ownerId);

}
