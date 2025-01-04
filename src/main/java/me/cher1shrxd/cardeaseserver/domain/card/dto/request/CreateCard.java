package me.cher1shrxd.cardeaseserver.domain.card.dto.request;

public record CreateCard(
        String email,
        String name,
        String introduce,
        String job,
        String company,
        String tel,
        String address,
        String background,
        String logo
) {
}
