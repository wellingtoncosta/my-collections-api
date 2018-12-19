package br.com.wellingtoncosta.mycollections.api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * @author Wellington Costa on 19/12/2018.
 */
@Data @Builder public class ErrorMessage {

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

}
