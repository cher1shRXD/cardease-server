package me.cher1shrxd.cardeaseserver.domain.auth.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
