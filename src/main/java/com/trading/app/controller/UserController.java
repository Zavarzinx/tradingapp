package com.trading.app.controller;

import com.trading.app.dto.UserDto;
import com.trading.app.entity.User;
import com.trading.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        log.info(userDto + "in REG");

       return new ResponseEntity<>(userService.register(UserDto.toUser(userDto)), HttpStatus.OK);
    }

    @GetMapping("/user/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(userService.findByUsername(userDetails.getUsername()), HttpStatus.OK);
    }
}
