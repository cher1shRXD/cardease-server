package me.cher1shrxd.cardeaseserver.domain.card.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.cher1shrxd.cardeaseserver.domain.card.dto.request.CreateCard;
import me.cher1shrxd.cardeaseserver.domain.card.service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Tag(name = "CARD")
public class CardController {
    private final CardService cardService;

    @PostMapping
    public void createCard(@RequestBody CreateCard createCard) {
        cardService.createCard(createCard);
    }
}
