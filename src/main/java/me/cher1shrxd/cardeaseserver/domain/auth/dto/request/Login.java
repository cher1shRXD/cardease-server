package me.cher1shrxd.cardeaseserver.domain.auth.dto.request;

public record Login(
        String email,
        String password
) {
}
