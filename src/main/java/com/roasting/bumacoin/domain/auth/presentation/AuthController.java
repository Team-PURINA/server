package com.roasting.bumacoin.domain.auth.presentation;

import com.roasting.bumacoin.domain.auth.service.OAuth2GoogleService;
import com.roasting.bumacoin.domain.auth.service.RefreshTokenService;
import com.roasting.bumacoin.global.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2GoogleService googleService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/google")
    public TokenResponseDto loginOfGoogle(@Validated @RequestParam(name = "code") String code) {
        return googleService.execute(code);
    }

    @PutMapping()
    public TokenResponseDto refreshToken(@RequestHeader(value = "Refresh-Token") @NotBlank String refreshToken) {
        return refreshTokenService.execute(refreshToken);
    }
}