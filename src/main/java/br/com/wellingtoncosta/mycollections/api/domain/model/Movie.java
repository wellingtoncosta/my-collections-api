package br.com.wellingtoncosta.mycollections.api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Data @AllArgsConstructor @NoArgsConstructor public class Movie {

    private Long id;

    private String name;

    private String description;

    private String director;

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private boolean favorite;

    private Long ownerId;

}
