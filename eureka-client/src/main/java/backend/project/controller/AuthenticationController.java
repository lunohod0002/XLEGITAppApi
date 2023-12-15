package backend.project.controller;

import backend.project.dto.AuthenticationRequest;
import backend.project.dto.AuthenticationResponse;
import backend.project.dto.RegisterRequest;
import backend.project.service.implementation.AuthServiceImpl;

import backend.project.util.ErrorBuilder;
import backend.project.util.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthServiceImpl authenticateService;
    private final ErrorBuilder errorBuilder;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request)  {


        return ResponseEntity.ok(authenticateService.register(request));
    }

    @PostMapping("/sign_in")
    public ResponseEntity<AuthenticationResponse> auth(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(authenticateService.authenticate(request));
    }


}
