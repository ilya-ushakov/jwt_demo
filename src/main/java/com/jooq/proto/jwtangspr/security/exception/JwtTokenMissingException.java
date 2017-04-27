package com.jooq.proto.jwtangspr.security.exception;

import org.springframework.security.core.AuthenticationException;


public class JwtTokenMissingException extends AuthenticationException {


    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
