package team404.aster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.aster.domain.dto.SignInDto;
import team404.aster.domain.dto.TokenDto;
import team404.aster.services.SignInService;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;


    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(signInService.signIn(signInDto));
    }
}
