package br.com.wellingtoncosta.mycollections.api.web.security.filter;

import br.com.wellingtoncosta.mycollections.api.domain.model.Credentials;
import br.com.wellingtoncosta.mycollections.api.domain.model.User;
import br.com.wellingtoncosta.mycollections.api.web.security.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Collections.emptyList;

/**
 * @author Wellington Costa on 19/12/2018.
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private User user;

    private final ObjectMapper mapper;

    public JwtLoginFilter(
            String url,
            AuthenticationManager authManager,
            ObjectMapper mapper
    ) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.mapper = mapper;
    }

    @Override public Authentication attemptAuthentication(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws AuthenticationException, IOException {
        Credentials credentials = mapper.readValue(req.getInputStream(), Credentials.class);
        Authentication authentication = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        emptyList()
                )
        );

        this.user = (User) authentication.getPrincipal();
        return authentication;
    }

    @Override protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth
    ) throws IOException {
        res.getWriter().print(mapper.writeValueAsString(user));
        JwtUtils.addAuthentication(res, auth.getName());
    }

}
