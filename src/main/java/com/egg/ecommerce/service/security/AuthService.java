package com.egg.ecommerce.service.security;

import com.egg.ecommerce.DTOs.AuthResponse;
import com.egg.ecommerce.DTOs.LoginRequest;
import com.egg.ecommerce.DTOs.RegisterRequest;
import com.egg.ecommerce.entity.Role;
import com.egg.ecommerce.entity.User;
import com.egg.ecommerce.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        User user;

        user = mapper.convertValue(request,User.class);

        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        String token = jwtService.getToken(user);
        authResponse.setToken(token);

        return authResponse;
    }
}
