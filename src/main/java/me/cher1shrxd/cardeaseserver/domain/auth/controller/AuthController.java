package me.cher1shrxd.cardeaseserver.domain.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.cher1shrxd.cardeaseserver.domain.auth.dto.request.Login;
import me.cher1shrxd.cardeaseserver.domain.auth.dto.request.Reissue;
import me.cher1shrxd.cardeaseserver.domain.auth.dto.request.Signup;
import me.cher1shrxd.cardeaseserver.domain.auth.service.AuthService;
import me.cher1shrxd.cardeaseserver.global.security.jwt.dto.JwtResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AUTH")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody Signup signup) {
            authService.signup(signup);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody Login login) {
        return authService.login(login);
    }

    @PostMapping("/reissue")
    public JwtResponse reissue(@RequestBody Reissue reissue) {
        return authService.reissue(reissue);
    }
}
