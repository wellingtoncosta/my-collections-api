package br.com.wellingtoncosta.mycollections.api.data.mapper;

import br.com.wellingtoncosta.mycollections.api.data.entity.MovieEntity;
import br.com.wellingtoncosta.mycollections.api.domain.model.Movie;

import static java.util.Objects.isNull;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public final class MovieMapper {

    private MovieMapper() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static Movie toDomain(MovieEntity entity) {
        if(isNull(entity)) {
            return null;
        }

        return new Movie(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDirector(),
                entity.getReleaseDate(),
                entity.isFavorite(),
                entity.getOwner() != null ? entity.getOwner().getId() : null
        );
    }

    public static MovieEntity toEntity(Movie movie) {
        if(isNull(movie)) {
            return null;
        }

        return new MovieEntity(
                movie.getId(),
                movie.getName(),
                movie.getDescription(),
                movie.getDirector(),
                movie.getReleaseDate(),
                movie.isFavorite(),
                null
        );
    }

}
