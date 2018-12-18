package br.com.wellingtoncosta.mycollections.api.domain.service.imp;

import br.com.wellingtoncosta.mycollections.api.data.entity.BookEntity;
import br.com.wellingtoncosta.mycollections.api.data.mapper.BookMapper;
import br.com.wellingtoncosta.mycollections.api.data.repository.BookRepository;
import br.com.wellingtoncosta.mycollections.api.domain.exception.BookNotFoundException;
import br.com.wellingtoncosta.mycollections.api.domain.model.Book;
import br.com.wellingtoncosta.mycollections.api.domain.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import static br.com.wellingtoncosta.mycollections.api.data.mapper.BookMapper.toDomain;
import static br.com.wellingtoncosta.mycollections.api.data.mapper.BookMapper.toEntity;
import static java.util.stream.Collectors.toList;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Service public class BookServiceImp implements BookService {

    private final BookRepository repository;

    @Autowired public BookServiceImp(BookRepository repository) {
        this.repository = repository;
    }

    @Override @Transactional public Book save(Book book) {
        return toDomain(repository.save(toEntity(book)));
    }

    @Override @Transactional public Book update(Long id, Book book) {
        Optional<BookEntity> bookDb = repository.findById(id);

        if(!bookDb.isPresent()) {
            throw new BookNotFoundException(id);
        }

        return toDomain(repository.save(toEntity(book)));
    }

    @Override @Transactional public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override public List<Book> findAll() {
        Spliterator<BookEntity> spliterator = repository.findAll().spliterator();
        return StreamSupport.stream(spliterator, false)
                .map(BookMapper::toDomain)
                .collect(toList());
    }

    @Override public Book findById(Long id) {
        Optional<BookEntity> bookDb = repository.findById(id);

        if(!bookDb.isPresent()) {
            throw new BookNotFoundException(id);
        }

        return toDomain(bookDb.get());
    }

}
