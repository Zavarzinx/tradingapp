package com.trading.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN,reason = "This user is not activated")
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}