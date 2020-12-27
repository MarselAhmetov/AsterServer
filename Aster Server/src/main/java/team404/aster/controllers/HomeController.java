package team404.aster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team404.aster.domain.User;
import team404.aster.repositories.UserRepository;
import team404.aster.security.jwt.details.UserDetailsImpl;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
