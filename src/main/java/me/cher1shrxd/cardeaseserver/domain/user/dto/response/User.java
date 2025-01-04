package me.cher1shrxd.cardeaseserver.domain.user.dto.response;

import me.cher1shrxd.cardeaseserver.domain.user.entity.UserEntity;
import me.cher1shrxd.cardeaseserver.domain.user.enums.UserRole;

public record User(
        Long id,
        String username,
        String email,
        String introduce,
        String tel,
        String company,
        String job,
        UserRole role
) {


    public static User of(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getIntroduce(),
                userEntity.getTel(),
                userEntity.getCompany(),
                userEntity.getJob(),
                userEntity.getRole()
        );
    }
}
