package br.com.wellingtoncosta.mycollections.api.domain.service.imp;

import br.com.wellingtoncosta.mycollections.api.data.entity.BookEntity;
import br.com.wellingtoncosta.mycollections.api.data.entity.UserEntity;
import br.com.wellingtoncosta.mycollections.api.data.mapper.BookMapper;
import br.com.wellingtoncosta.mycollections.api.data.repository.BookRepository;
import br.com.wellingtoncosta.mycollections.api.data.repository.UserRepository;
import br.com.wellingtoncosta.mycollections.api.domain.exception.BookNotFoundException;
import br.com.wellingtoncosta.mycollections.api.domain.exception.UserNotFoundException;
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

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired public BookServiceImp(
            BookRepository bookRepository,
            UserRepository userRepository
    ) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    private BookEntity getBookEntityWithOwner(Book book) {
        Optional<UserEntity> userDb = userRepository.findById(book.getOwnerId());

        if(!userDb.isPresent()) {
            throw new UserNotFoundException(book.getOwnerId());
        }

        BookEntity entity = toEntity(book);
        assert entity != null;
        entity.setOwner(userDb.get());
        return entity;
    }

    @Override @Transactional public Book save(Book book) {
        BookEntity entity = getBookEntityWithOwner(book);
        return toDomain(bookRepository.save(entity));
    }

    @Override @Transactional public Book update(Long id, Book book) {
        Optional<BookEntity> bookDb = bookRepository.findById(id);

        if(!bookDb.isPresent()) {
            throw new BookNotFoundException(id);
        }

        BookEntity entity = getBookEntityWithOwner(book);
        return toDomain(bookRepository.save(entity));
    }

    @Override @Transactional public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override public List<Book> findAll() {
        Spliterator<BookEntity> spliterator = bookRepository.findAll().spliterator();
        return StreamSupport.stream(spliterator, false)
                .map(BookMapper::toDomain)
                .collect(toList());
    }

    @Override public List<Book> findAllByOwner(Long ownerId) {
        return bookRepository.findAllByOwner(ownerId).stream()
                .map(BookMapper::toDomain)
                .collect(toList());
    }

    @Override public Book findById(Long id) {
        Optional<BookEntity> bookDb = bookRepository.findById(id);

        if(!bookDb.isPresent()) {
            throw new BookNotFoundException(id);
        }

        return toDomain(bookDb.get());
    }

}
