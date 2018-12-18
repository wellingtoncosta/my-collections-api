package br.com.wellingtoncosta.mycollections.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Data @AllArgsConstructor @NoArgsConstructor public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

}
