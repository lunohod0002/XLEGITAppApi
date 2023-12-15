package backend.project.service;


import backend.project.dto.AuthenticationRequest;
import backend.project.dto.AuthenticationResponse;
import backend.project.dto.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest registerRequest);



}
