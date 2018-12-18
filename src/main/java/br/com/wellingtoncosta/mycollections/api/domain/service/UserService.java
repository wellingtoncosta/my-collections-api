package br.com.wellingtoncosta.mycollections.api.domain.service;

import br.com.wellingtoncosta.mycollections.api.domain.model.Credentials;
import br.com.wellingtoncosta.mycollections.api.domain.model.User;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public interface UserService {

    User save(User user);

    User authenticate(Credentials credentials);

}
