package br.com.wellingtoncosta.mycollections.api.data.mapper;

import br.com.wellingtoncosta.mycollections.api.data.entity.BookEntity;
import br.com.wellingtoncosta.mycollections.api.domain.model.Book;

/**
 * @author Wellington Costa on 18/12/2018.
 */
public final class BookMapper {

    private BookMapper() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static Book toDomain(BookEntity entity) {
        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getReleaseDate(),
                entity.isFavorite()
        );
    }

    public static BookEntity toEntity(Book book) {
        return new BookEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.isFavorite()
        );
    }

}
