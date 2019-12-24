package com.trading.app.service;

import com.trading.app.entity.User;

public interface UserService {
    User register(User user);

    User save(User user);

    User findByUsername(String username);
}
