package br.com.wellingtoncosta.mycollections.api.web.controller;

import br.com.wellingtoncosta.mycollections.api.domain.model.User;
import br.com.wellingtoncosta.mycollections.api.domain.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.Objects.isNull;
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

}
