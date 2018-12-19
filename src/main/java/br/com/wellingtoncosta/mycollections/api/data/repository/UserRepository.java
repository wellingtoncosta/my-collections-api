package br.com.wellingtoncosta.mycollections.api.data.repository;

import br.com.wellingtoncosta.mycollections.api.data.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Repository public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("from UserEntity user where user.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

}
