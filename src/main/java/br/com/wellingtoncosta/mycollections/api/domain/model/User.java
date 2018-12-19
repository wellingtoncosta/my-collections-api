package br.com.wellingtoncosta.mycollections.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * @author Wellington Costa on 18/12/2018.
 */
@Data @AllArgsConstructor @NoArgsConstructor public class User implements UserDetails {

    private Long id;

    private String name;

    private String email;

    @JsonProperty(access = WRITE_ONLY)
    private String password;

    @Override @JsonIgnore public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override @JsonIgnore public String getUsername() {
        return email;
    }

    @Override @JsonIgnore public boolean isAccountNonExpired() {
        return true;
    }

    @Override @JsonIgnore public boolean isAccountNonLocked() {
        return true;
    }

    @Override @JsonIgnore public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override @JsonIgnore public boolean isEnabled() {
        return true;
    }

}
