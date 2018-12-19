package br.com.wellingtoncosta.mycollections.api.domain.service;

import br.com.wellingtoncosta.mycollections.api.domain.model.Book;

import java.util.List;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public interface BookService extends CrudService<Book, Long> {

    List<Book> findAllByOwner(Long ownerId);

}
