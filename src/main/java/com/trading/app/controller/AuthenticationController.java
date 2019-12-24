package com.trading.app.controller;

import com.trading.app.dto.AuthenticationRequestDto;
import com.trading.app.entity.User;
import com.trading.app.exception.JwtAuthenticationException;
import com.trading.app.security.jwt.JwtTokenProvider;
import com.trading.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        if (!user.isActive()){
            throw new JwtAuthenticationException("User with username: " + username + "is not activated");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

        String token = jwtTokenProvider.createToken(username, user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        response.put("roles",user.getRoles());

        return ResponseEntity.ok(response);
    }
}
