package br.com.wellingtoncosta.mycollections.api.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Wellington Costa on 19/12/2018.
 */
@ResponseStatus(BAD_REQUEST) public class EmptyUsernameException extends RuntimeException {

    public EmptyUsernameException() {
        super("The field 'username' cannot be empty or null");
    }

}
