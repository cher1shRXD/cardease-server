package me.cher1shrxd.cardeaseserver.domain.card.service;

import lombok.RequiredArgsConstructor;
import me.cher1shrxd.cardeaseserver.domain.card.dto.request.CreateCard;
import me.cher1shrxd.cardeaseserver.domain.card.entity.CardEntity;
import me.cher1shrxd.cardeaseserver.domain.card.repository.CardRepository;
import me.cher1shrxd.cardeaseserver.domain.user.entity.UserEntity;
import me.cher1shrxd.cardeaseserver.domain.user.repository.UserRepository;
import me.cher1shrxd.cardeaseserver.global.exception.CustomErrorCode;
import me.cher1shrxd.cardeaseserver.global.exception.CustomException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public void createCard(CreateCard createCard) {
        UserEntity user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        CardEntity card = CardEntity.builder()
                .user(user)
                .email(createCard.email())
                .name(createCard.name())
                .tel(createCard.tel())
                .company(createCard.company())
                .address(createCard.address())
                .introduce(createCard.introduce())
                .background(createCard.background())
                .logo(createCard.logo())
                .job(createCard.job())
                .build();

        cardRepository.save(card);
    }
}
