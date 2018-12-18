package br.com.wellingtoncosta.mycollections.api.data.mapper;

import br.com.wellingtoncosta.mycollections.api.data.entity.UserEntity;
import br.com.wellingtoncosta.mycollections.api.domain.model.User;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public final class UserMapper {

    private UserMapper() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

}
