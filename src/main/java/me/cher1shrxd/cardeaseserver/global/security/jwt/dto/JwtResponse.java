package me.cher1shrxd.cardeaseserver.global.security.jwt.dto;

public record JwtResponse(
        String accessToken,
        String refreshToken
) {
}
