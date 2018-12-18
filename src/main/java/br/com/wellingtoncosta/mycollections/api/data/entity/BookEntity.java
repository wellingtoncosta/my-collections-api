package br.com.wellingtoncosta.mycollections.api.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "release_date", columnDefinition = "DATE", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "favorite", nullable = false)
    private boolean favorite;

}
