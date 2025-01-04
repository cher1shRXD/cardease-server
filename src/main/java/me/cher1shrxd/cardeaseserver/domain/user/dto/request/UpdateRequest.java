package me.cher1shrxd.cardeaseserver.domain.user.dto.request;

public record UpdateRequest(
        String username,
        String password,
        String currentPassword
) {
}
