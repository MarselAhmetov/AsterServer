package team404.aster.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team404.aster.domain.Role;
import team404.aster.security.jwt.authentication.JwtAuthentication;
import team404.aster.security.jwt.details.UserDetailsImpl;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Value("8364c0m43nt6vc29mp")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();

        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }

        UserDetails userDetails = UserDetailsImpl.builder()
                .userId(claims.get("userId", String.class))
                .username(claims.get("username", String.class))
                .password(claims.get("password", String.class))
                .role(claims.get("role", Role.class))
                .build();
        authentication.setAuthenticated(true);
        ((JwtAuthentication) authentication).setUserDetails(userDetails);

        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthentication.class.equals(aClass);
    }
}
