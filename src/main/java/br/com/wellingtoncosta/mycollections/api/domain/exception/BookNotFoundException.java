package br.com.wellingtoncosta.mycollections.api.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@ResponseStatus(NOT_FOUND) public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("The book with identifier " + id  + " was not found.");
    }

}
