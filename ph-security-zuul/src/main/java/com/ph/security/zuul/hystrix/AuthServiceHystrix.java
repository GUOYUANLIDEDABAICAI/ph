package com.ph.security.zuul.hystrix;

import com.ph.security.common.entity.User;
import com.ph.security.zuul.service.IAuthService;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements IAuthService {
}
