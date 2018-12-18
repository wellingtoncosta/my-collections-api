package br.com.wellingtoncosta.mycollections.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Data @AllArgsConstructor @NoArgsConstructor public class Movie {

    private Long id;
    private String name;
    private String description;
    private String director;
    private LocalDate releaseDate;
    private boolean favorite;

}
