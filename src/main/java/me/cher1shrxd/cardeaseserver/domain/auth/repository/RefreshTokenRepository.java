package me.cher1shrxd.cardeaseserver.domain.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public void save(String email, String refreshToken) {
        redisTemplate.opsForValue().set("refreshToken:" + email, refreshToken);
    }

    public void deleteByEmail(String email) {
        redisTemplate.delete("refreshToken:" + email);
    }

    public String findByEmail(String email) {
        return redisTemplate.opsForValue().get("refreshToken:" + email);
    }

    public Boolean existsByEmail(String email) {
        return redisTemplate.hasKey("refreshToken:" + email);
    }
}
