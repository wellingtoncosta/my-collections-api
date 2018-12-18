package br.com.wellingtoncosta.mycollections.api.web;

import br.com.wellingtoncosta.mycollections.api.domain.model.Book;
import br.com.wellingtoncosta.mycollections.api.domain.service.BookService;
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
@RequestMapping("api/books")
public class BookController {

    private final BookService service;

    @Autowired public BookController(BookService service) {
        this.service = service;
    }

    @ApiOperation(value = "Save a new book.")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> save(@Valid @RequestBody Book book) {
        if(isNull(book)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(CREATED).body(service.save(book));
    }

    @ApiOperation(value = "Update a specific book by id.")
    @PutMapping(
            value= "/{id}",
            consumes = APPLICATION_JSON_UTF8_VALUE,
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Book> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Book book
    ) {
        if(isNull(book)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(OK).body(service.update(id, book));
    }

    @ApiOperation(value = "Delete a book by id.")
    @DeleteMapping(value= "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @ApiOperation(value = "List all books.")
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = service.findAll();
        if(books.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(books);
        }
    }

    @ApiOperation(value = "Find a specific book by id.")
    @GetMapping(value= "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
