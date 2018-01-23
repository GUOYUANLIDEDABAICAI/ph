package com.ph.security.agent.exception;

public class AuthenticationServerErrorException extends RuntimeException {
    public AuthenticationServerErrorException(String message) {
        super(message);
    }
}
