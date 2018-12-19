package br.com.wellingtoncosta.mycollections.api.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@ResponseStatus(NOT_FOUND) public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("The user with email " + email + " was not found");
    }

    public UserNotFoundException(Long id) {
        super("The user with identifier " + id + " was not found");
    }

}
