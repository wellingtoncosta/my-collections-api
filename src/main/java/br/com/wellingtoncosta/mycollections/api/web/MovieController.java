package br.com.wellingtoncosta.mycollections.api.web;

import br.com.wellingtoncosta.mycollections.api.domain.model.Book;
import br.com.wellingtoncosta.mycollections.api.domain.model.Movie;
import br.com.wellingtoncosta.mycollections.api.domain.service.MovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final MovieService service;

    @Autowired public MovieController(MovieService service) {
        this.service = service;
    }

    @ApiOperation(value = "Save a new movie.")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Movie> save(@Valid @RequestBody Movie movie) {
        if(isNull(movie)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(CREATED).body(service.save(movie));
    }

    @ApiOperation(value = "Update a specific movie by id.")
    @PutMapping(
            value= "/{id}",
            consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Movie> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Movie movie
    ) {
        if(isNull(movie)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(OK).body(service.update(id, movie));
    }

    @ApiOperation(value = "Delete a movie by id.")
    @DeleteMapping(value= "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @ApiOperation(value = "List all movies.")
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Movie>> findAll() {
        List<Movie> movies = service.findAll();
        if(movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }

    @ApiOperation(value = "List all movies by its owner.")
    @GetMapping(value= "/owner/{ownerId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Movie>> findAllByOwner(@PathVariable("ownerId") Long ownerId) {
        List<Movie> movies = service.findAllByOwner(ownerId);
        if(movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }

    @ApiOperation(value = "Find a specific movie by id.")
    @GetMapping(value= "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
