package br.com.wellingtoncosta.mycollections.api.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

/**
 * @author Wellington Costa on 19/12/2018.
 */
public final class JwtUtils {

    private JwtUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    private static final String SECRET = "bXktYzBsbDNjdDEwbnMtYXAxLXMzY3IzdA==";
    private static final String HEADER_AUTHORIZATION = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        res.addHeader(HEADER_AUTHORIZATION, JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);

        if (!isNull(token)) {
            String email = getEmail(token);

            if (!isNull(email)) {
                return new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        emptyList()
                );
            }
        }

        return null;
    }

    private static String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}