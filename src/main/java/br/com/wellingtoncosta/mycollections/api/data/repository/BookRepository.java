package br.com.wellingtoncosta.mycollections.api.data.repository;

import br.com.wellingtoncosta.mycollections.api.data.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Repository public interface BookRepository extends CrudRepository<BookEntity, Long> {

}
