package team404.aster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.aster.domain.Role;
import team404.aster.domain.User;
import team404.aster.domain.dto.SignUpDto;
import team404.aster.repositories.UserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }
}
