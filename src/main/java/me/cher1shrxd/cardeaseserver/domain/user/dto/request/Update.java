package me.cher1shrxd.cardeaseserver.domain.user.dto.request;

public record Update(
        String username,
        String password,
        String introduce,
        String tel,
        String company,
        String job,
        String currentPassword
) {
}
