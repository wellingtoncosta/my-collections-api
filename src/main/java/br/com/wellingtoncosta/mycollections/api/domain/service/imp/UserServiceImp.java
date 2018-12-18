package br.com.wellingtoncosta.mycollections.api.domain.service.imp;

import br.com.wellingtoncosta.mycollections.api.data.repository.UserRepository;
import br.com.wellingtoncosta.mycollections.api.domain.model.Credentials;
import br.com.wellingtoncosta.mycollections.api.domain.model.User;
import br.com.wellingtoncosta.mycollections.api.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.wellingtoncosta.mycollections.api.data.mapper.UserMapper.toDomain;
import static br.com.wellingtoncosta.mycollections.api.data.mapper.UserMapper.toEntity;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Service public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Autowired public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override public User save(User user) {
        return toDomain(repository.save(toEntity(user)));
    }

    @Override public User authenticate(Credentials credentials) {
        return null;
    }

}
