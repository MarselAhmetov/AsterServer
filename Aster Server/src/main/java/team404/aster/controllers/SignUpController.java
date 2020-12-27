package team404.aster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.aster.domain.dto.SignUpDto;
import team404.aster.services.SignUpService;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto) {
        signUpService.signUp(signUpDto);
        return ResponseEntity.ok("Ok");
    }
}
