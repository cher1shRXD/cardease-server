package me.cher1shrxd.cardeaseserver.domain.card.repository;

import me.cher1shrxd.cardeaseserver.domain.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, UUID> {
}
