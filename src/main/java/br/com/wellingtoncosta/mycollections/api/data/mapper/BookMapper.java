package br.com.wellingtoncosta.mycollections.api.data.mapper;

import br.com.wellingtoncosta.mycollections.api.data.entity.BookEntity;
import br.com.wellingtoncosta.mycollections.api.domain.model.Book;

import static java.util.Objects.isNull;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public final class BookMapper {

    private BookMapper() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static Book toDomain(BookEntity entity) {
        if(isNull(entity)) {
            return null;
        }

        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getReleaseDate(),
                entity.isFavorite(),
                entity.getOwner() != null ? entity.getOwner().getId() : null
        );
    }

    public static BookEntity toEntity(Book book) {
        if(isNull(book)) {
            return null;
        }

        return new BookEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.isFavorite(),
                null
        );
    }

}
