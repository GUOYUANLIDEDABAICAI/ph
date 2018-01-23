package com.ph.security.agent.exception;

public class AuthenticationVerifyFailException extends RuntimeException {
    public AuthenticationVerifyFailException(String message) {
        super(message);
    }
}
