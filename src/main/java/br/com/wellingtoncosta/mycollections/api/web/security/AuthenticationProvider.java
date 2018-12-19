package br.com.wellingtoncosta.mycollections.api.web.security;

import br.com.wellingtoncosta.mycollections.api.data.entity.UserEntity;
import br.com.wellingtoncosta.mycollections.api.data.repository.UserRepository;
import br.com.wellingtoncosta.mycollections.api.domain.exception.EmptyPasswordException;
import br.com.wellingtoncosta.mycollections.api.domain.exception.EmptyUsernameException;
import br.com.wellingtoncosta.mycollections.api.domain.exception.InvalidPasswordException;
import br.com.wellingtoncosta.mycollections.api.domain.exception.UserNotFoundException;
import br.com.wellingtoncosta.mycollections.api.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.wellingtoncosta.mycollections.api.data.mapper.UserMapper.toDomain;
import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * @author Wellington Costa on 19/12/2018.
 */
@Service public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    private User user;

    @Autowired public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication
    ) throws AuthenticationException { }

    @Override protected UserDetails retrieveUser(
            String email,
            UsernamePasswordAuthenticationToken authentication
    ) throws AuthenticationException {
        validateUserEmail(email);
        validateUserPassword(email, authentication);
        return user;
    }

    private void validateUserEmail(String email) {
        if (isNull(email) || isEmpty(email)) {
            setHideUserNotFoundExceptions(false);
            throw new EmptyUsernameException();
        }
    }

    private void validateUserPassword(
            String email,
            UsernamePasswordAuthenticationToken authentication
    ) {
        String password = (String) authentication.getCredentials();

        if (isNull(password) || isEmpty(password)) {
            throw new EmptyPasswordException();
        }

        Optional<UserEntity> userDb = userRepository.findByEmail(email);

        if(!userDb.isPresent()) {
            throw new UserNotFoundException(email);
        }

        User user = toDomain(userDb.get());
        assert user != null;
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }

        this.user = user;
    }

}
