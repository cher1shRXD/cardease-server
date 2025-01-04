package me.cher1shrxd.cardeaseserver.domain.auth.dto.request;

public record Signup(
        String username,
        String email,
        String introduce,
        String tel,
        String company,
        String job,
        String password
) {
}
