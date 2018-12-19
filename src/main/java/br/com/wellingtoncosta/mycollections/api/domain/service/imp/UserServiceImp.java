package br.com.wellingtoncosta.mycollections.api.domain.service.imp;

import br.com.wellingtoncosta.mycollections.api.data.entity.UserEntity;
import br.com.wellingtoncosta.mycollections.api.data.repository.UserRepository;
import br.com.wellingtoncosta.mycollections.api.domain.exception.UserNotFoundException;
import br.com.wellingtoncosta.mycollections.api.domain.model.User;
import br.com.wellingtoncosta.mycollections.api.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.wellingtoncosta.mycollections.api.data.mapper.UserMapper.toDomain;
import static br.com.wellingtoncosta.mycollections.api.data.mapper.UserMapper.toEntity;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Service public class UserServiceImp implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired public UserServiceImp(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override public User findByEmail(String email) {
        Optional<UserEntity> userDb = repository.findByEmail(email);

        if(!userDb.isPresent()) {
            throw new UserNotFoundException(email);
        }

        return toDomain(userDb.get());
    }

    @Override public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return toDomain(repository.save(toEntity(user)));
    }

}
