package br.com.wellingtoncosta.mycollections.api.data.repository;

import br.com.wellingtoncosta.mycollections.api.data.entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Repository public interface BookRepository extends CrudRepository<BookEntity, Long> {

    @Query("from BookEntity book where book.owner.id = :ownerId")
    List<BookEntity> findAllByOwner(@Param("ownerId") Long ownerId);

}
