package team404.aster.services;

import team404.aster.domain.dto.SignInDto;
import team404.aster.domain.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
