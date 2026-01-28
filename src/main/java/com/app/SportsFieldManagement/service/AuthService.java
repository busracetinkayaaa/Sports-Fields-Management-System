package com.app.SportsFieldManagement.service;

import com.app.SportsFieldManagement.dto.request.LoginRequest;
import com.app.SportsFieldManagement.dto.response.LoginResponse;
import com.app.SportsFieldManagement.dto.request.RegisterRequest;
import com.app.SportsFieldManagement.dto.response.UserResponse;
import com.app.SportsFieldManagement.mapper.UserMapper;
import com.app.SportsFieldManagement.model.User;
import com.app.SportsFieldManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    public UserResponse register(RegisterRequest request){
        log.info("Register attempt for username: {}", request.username());
        if(userRepository.existsByUsername(request.username())){
            log.warn("Register failed - username already exists: {}", request.username());
            throw new RuntimeException("Username exists");
        }
        String encoded = passwordEncoder.encode(request.password());
        User user =userMapper.toEntity(request,encoded);
        userRepository.save(user);
        log.info("User registered successfully. username={}, role={}", user.getUsername(), user.getRole());
        return userMapper.toResponse(user);
    }
    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt for username: {}", request.username());
        Authentication authentication=authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                request.username(), request.password()
        ));
        log.info("Login successful for username: {}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("JWT token generated for username: {}", authentication.getName());
        return new LoginResponse(token);
    }
}
