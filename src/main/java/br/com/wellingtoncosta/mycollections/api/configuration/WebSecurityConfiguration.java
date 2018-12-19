package br.com.wellingtoncosta.mycollections.api.configuration;

import br.com.wellingtoncosta.mycollections.api.web.security.AuthenticationProvider;
import br.com.wellingtoncosta.mycollections.api.web.security.filter.JwtAuthenticationFilter;
import br.com.wellingtoncosta.mycollections.api.web.security.filter.JwtLoginFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;

/**
 * @author Wellington Costa on 19/12/2018.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private ObjectMapper objectMapper;
    private AuthenticationProvider authenticationProvider;

    @Autowired public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(POST, "/api/users").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .cors()
                .and()
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager(), objectMapper),
                        UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new JwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

}
