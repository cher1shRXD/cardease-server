package me.cher1shrxd.cardeaseserver.domain.auth.service;

import lombok.RequiredArgsConstructor;

import me.cher1shrxd.cardeaseserver.domain.auth.dto.request.Login;
import me.cher1shrxd.cardeaseserver.domain.auth.dto.request.Reissue;
import me.cher1shrxd.cardeaseserver.domain.auth.dto.request.Signup;
import me.cher1shrxd.cardeaseserver.domain.auth.repository.RefreshTokenRepository;
import me.cher1shrxd.cardeaseserver.domain.user.entity.UserEntity;
import me.cher1shrxd.cardeaseserver.domain.user.enums.UserRole;
import me.cher1shrxd.cardeaseserver.domain.user.repository.UserRepository;
import me.cher1shrxd.cardeaseserver.global.exception.CustomErrorCode;
import me.cher1shrxd.cardeaseserver.global.exception.CustomException;
import me.cher1shrxd.cardeaseserver.global.security.jwt.dto.JwtResponse;
import me.cher1shrxd.cardeaseserver.global.security.jwt.enums.JwtType;
import me.cher1shrxd.cardeaseserver.global.security.jwt.provider.JwtProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public void signup(Signup signup) {
        String hashedPassword = bCryptPasswordEncoder.encode(signup.password());

        if(userRepository.existsByUsername(signup.username())) {
            throw new CustomException(CustomErrorCode.USERNAME_DUPLICATION);
        }

        if(userRepository.existsByEmail(signup.email())) {
            throw new CustomException(CustomErrorCode.EMAIL_DUPLICATION);
        }

        UserEntity userEntity = UserEntity.builder()
                .username(signup.username())
                .email(signup.email())
                .password(hashedPassword)
                .introduce(signup.introduce())
                .tel(signup.tel())
                .company(signup.company())
                .job(signup.job())
                .role(UserRole.USER)
                .build();

        userRepository.save(userEntity);
    }

    public JwtResponse login(Login login) {
        String email = login.email();
        String password = login.password();

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        if (!bCryptPasswordEncoder.matches(password, userEntity.getPassword()))
            throw new CustomException(CustomErrorCode.WRONG_PASSWORD);

        JwtResponse token = jwtProvider.generateToken(email);

        refreshTokenRepository.save(email, token.refreshToken());

        return token;
    }

    public JwtResponse reissue(Reissue reissue) {
        String refreshToken = reissue.refreshToken();

        if (jwtProvider.getType(refreshToken) != JwtType.REFRESH)
            throw new CustomException(CustomErrorCode.INVALID_TOKEN_TYPE);

        String email = jwtProvider.getSubject(refreshToken);

        if (!refreshTokenRepository.existsByEmail(email))
            throw new CustomException(CustomErrorCode.INVALID_REFRESH_TOKEN);

        if (!refreshTokenRepository.findByEmail(email).equals(refreshToken))
            throw new CustomException(CustomErrorCode.INVALID_REFRESH_TOKEN);

        if (!userRepository.existsByUsername(email)) throw new CustomException(CustomErrorCode.USER_NOT_FOUND);

        return jwtProvider.generateToken(email);
    }
}
