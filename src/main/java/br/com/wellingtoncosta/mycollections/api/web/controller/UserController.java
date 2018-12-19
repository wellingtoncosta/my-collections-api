package br.com.wellingtoncosta.mycollections.api.web.controller;

import br.com.wellingtoncosta.mycollections.api.domain.model.ErrorMessage;
import br.com.wellingtoncosta.mycollections.api.domain.model.User;
import br.com.wellingtoncosta.mycollections.api.domain.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService service;

    @Autowired public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "Save a new user.")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        if(isNull(user)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(CREATED).body(service.save(user));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> duplicateEmailException(
            DataIntegrityViolationException e
    ) {
        return ResponseEntity.status(CONFLICT)
                .body(
                        ErrorMessage.builder()
                                .error("Conflict")
                                .message(e.getLocalizedMessage())
                                .status(CONFLICT.value())
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

}
