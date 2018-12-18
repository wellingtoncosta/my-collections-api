package br.com.wellingtoncosta.mycollections.api.domain.service.imp;

import br.com.wellingtoncosta.mycollections.api.data.entity.MovieEntity;
import br.com.wellingtoncosta.mycollections.api.data.mapper.MovieMapper;
import br.com.wellingtoncosta.mycollections.api.data.repository.MovieRepository;
import br.com.wellingtoncosta.mycollections.api.domain.exception.MovieNotFoundException;
import br.com.wellingtoncosta.mycollections.api.domain.model.Movie;
import br.com.wellingtoncosta.mycollections.api.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import static br.com.wellingtoncosta.mycollections.api.data.mapper.MovieMapper.toDomain;
import static br.com.wellingtoncosta.mycollections.api.data.mapper.MovieMapper.toEntity;
import static java.util.stream.Collectors.toList;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Service public class MovieServiceImp implements MovieService {

    private final MovieRepository repository;

    @Autowired public MovieServiceImp(MovieRepository repository) {
        this.repository = repository;
    }

    @Override @Transactional public Movie save(Movie movie) {
        return toDomain(repository.save(toEntity(movie)));
    }

    @Override @Transactional public Movie update(Long id, Movie movie) {
        Optional<MovieEntity> movieDb = repository.findById(id);

        if(!movieDb.isPresent()) {
            throw new MovieNotFoundException(id);
        }

        return toDomain(repository.save(toEntity(movie)));
    }

    @Override @Transactional public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override public List<Movie> findAll() {
        Spliterator<MovieEntity> spliterator = repository.findAll().spliterator();
        return StreamSupport.stream(spliterator, false)
                .map(MovieMapper::toDomain)
                .collect(toList());
    }

    @Override public Movie findById(Long id) {
        Optional<MovieEntity> movieDb = repository.findById(id);

        if(!movieDb.isPresent()) {
            throw new MovieNotFoundException(id);
        }

        return toDomain(movieDb.get());
    }

}
