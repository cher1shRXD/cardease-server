package me.cher1shrxd.cardeaseserver.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.cher1shrxd.cardeaseserver.domain.user.dto.request.Update;
import me.cher1shrxd.cardeaseserver.domain.user.dto.response.User;
import me.cher1shrxd.cardeaseserver.domain.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "USER")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public User getMe() {
        return userService.getMe();
    }

    @PatchMapping("/me")
    public User updateMe(@RequestBody Update update) {
        System.out.println(update);
        return userService.updateMe(update);
    }
}
