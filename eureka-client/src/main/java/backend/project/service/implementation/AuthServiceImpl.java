package backend.project.service.implementation;


import backend.project.security.JwtService;
import backend.project.dto.AuthenticationRequest;
import backend.project.dto.AuthenticationResponse;
import backend.project.dto.RegisterRequest;
import backend.project.entity.Role;
import backend.project.entity.User;
import backend.project.exception.UserNotFoundException;
import backend.project.repository.UserRepository;
import backend.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getEmail())
                .username(request.getEmail())
                .email (request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findUserByEmail(request.getEmail());
        var existingUser = user.orElseThrow(() ->
                new UserNotFoundException("User " + " not found"));

        var jwtToken = jwtService.generateToken(existingUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}

