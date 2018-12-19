package br.com.wellingtoncosta.mycollections.api.data.repository;

import br.com.wellingtoncosta.mycollections.api.data.entity.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Repository public interface MovieRepository extends CrudRepository<MovieEntity, Long> {

    @Query("from MovieEntity movie where movie.owner.id = :ownerId")
    List<MovieEntity> findAllByOwner(@Param("ownerId") Long ownerId);

}
