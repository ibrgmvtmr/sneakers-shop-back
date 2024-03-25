package edu.alatoo.sneakers.controller;

import edu.alatoo.sneakers.payload.LoginRequestDTO;
import edu.alatoo.sneakers.payload.SignupRequestDTO;
import edu.alatoo.sneakers.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequestDTO signUpRequest) {
        authService.register(signUpRequest);
        return new ResponseEntity<>("Пользователь успешно зарегестрирован", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        authService.authenticate(loginRequestDTO);
        return new ResponseEntity<>("Добро пожаловать в систему!", HttpStatus.OK);
    }
}
