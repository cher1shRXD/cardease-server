package me.cher1shrxd.cardeaseserver.domain.auth.dto.request;

public record SignupRequest(
        String username,
        String email,
        String password
) {
}
