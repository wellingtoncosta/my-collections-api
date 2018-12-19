package br.com.wellingtoncosta.mycollections.api.domain.service.imp;

import br.com.wellingtoncosta.mycollections.api.data.entity.MovieEntity;
import br.com.wellingtoncosta.mycollections.api.data.entity.UserEntity;
import br.com.wellingtoncosta.mycollections.api.data.mapper.MovieMapper;
import br.com.wellingtoncosta.mycollections.api.data.repository.MovieRepository;
import br.com.wellingtoncosta.mycollections.api.data.repository.UserRepository;
import br.com.wellingtoncosta.mycollections.api.domain.exception.MovieNotFoundException;
import br.com.wellingtoncosta.mycollections.api.domain.exception.UserNotFoundException;
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

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Autowired public MovieServiceImp(
            MovieRepository movieRepository,
            UserRepository userRepository
    ) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    private MovieEntity getMovieEntityWithOwner(Movie movie) {
        Optional<UserEntity> userDb = userRepository.findById(movie.getOwnerId());

        if(!userDb.isPresent()) {
            throw new UserNotFoundException(movie.getOwnerId());
        }

        MovieEntity entity = toEntity(movie);
        assert entity != null;
        entity.setOwner(userDb.get());
        return entity;
    }

    @Override @Transactional public Movie save(Movie movie) {
        MovieEntity entity = getMovieEntityWithOwner(movie);
        return toDomain(movieRepository.save(entity));
    }

    @Override @Transactional public Movie update(Long id, Movie movie) {
        Optional<MovieEntity> movieDb = movieRepository.findById(id);

        if(!movieDb.isPresent()) {
            throw new MovieNotFoundException(id);
        }

        return toDomain(movieRepository.save(toEntity(movie)));
    }

    @Override @Transactional public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    @Override public List<Movie> findAll() {
        Spliterator<MovieEntity> spliterator = movieRepository.findAll().spliterator();
        return StreamSupport.stream(spliterator, false)
                .map(MovieMapper::toDomain)
                .collect(toList());
    }

    @Override public List<Movie> findAllByOwner(Long ownerId) {
        return movieRepository.findAllByOwner(ownerId).stream()
                .map(MovieMapper::toDomain)
                .collect(toList());
    }

    @Override public Movie findById(Long id) {
        Optional<MovieEntity> movieDb = movieRepository.findById(id);

        if(!movieDb.isPresent()) {
            throw new MovieNotFoundException(id);
        }

        return toDomain(movieDb.get());
    }

}
