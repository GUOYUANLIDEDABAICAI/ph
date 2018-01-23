package com.ph.security.zuul.future;

import com.ph.security.common.entity.User;
import com.ph.security.zuul.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AuthFuture {
    @Autowired
    private IAuthService authService;

}
