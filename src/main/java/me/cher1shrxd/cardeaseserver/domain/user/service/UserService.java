package me.cher1shrxd.cardeaseserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.cher1shrxd.cardeaseserver.domain.user.dto.request.Update;
import me.cher1shrxd.cardeaseserver.domain.user.dto.response.User;
import me.cher1shrxd.cardeaseserver.domain.user.entity.UserEntity;
import me.cher1shrxd.cardeaseserver.domain.user.repository.UserRepository;
import me.cher1shrxd.cardeaseserver.global.exception.CustomErrorCode;
import me.cher1shrxd.cardeaseserver.global.exception.CustomException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getMe() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return new User(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getIntroduce(), userEntity.getTel(), userEntity.getCompany(), userEntity.getJob(), userEntity.getRole());
    }

    public User updateMe(Update update) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        if(update.currentPassword() == null || !bCryptPasswordEncoder.matches(update.currentPassword(), userEntity.getPassword())) {
            throw new CustomException(CustomErrorCode.WRONG_PASSWORD);
        }

        if (update.username() != null) userEntity.setUsername(update.username());
        if(update.tel() != null) userEntity.setTel(update.tel());
        if(update.company() != null) userEntity.setCompany(update.company());
        if(update.job() != null) userEntity.setJob(update.job());
        if (update.password() != null) {
            String hashedPassword = bCryptPasswordEncoder.encode(update.password());
            userEntity.setPassword(hashedPassword);
        }

        userRepository.save(userEntity);

        return new User(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getIntroduce(), userEntity.getTel(), userEntity.getCompany(), userEntity.getJob(), userEntity.getRole());
    }
}
