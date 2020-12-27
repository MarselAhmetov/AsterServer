package team404.aster.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import team404.aster.domain.User;
import team404.aster.domain.dto.SignInDto;
import team404.aster.domain.dto.TokenDto;
import team404.aster.repositories.UserRepository;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("8364c0m43nt6vc29mp")
    private String secret;

    @Override
    public TokenDto signIn(SignInDto signInDto) {
        Optional<User> userOptional = userRepository.findUserByUsername(signInDto.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
                String token = Jwts.builder()
                        .setSubject(user.getId())
                        .claim("username", user.getUsername())
                        .claim("role", user.getRole())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new IllegalArgumentException("Wrong username/password");
        } else throw new IllegalArgumentException("User not found");
    }
}
