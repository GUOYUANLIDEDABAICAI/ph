package com.ph.security.zuul.service;

import com.ph.security.common.entity.User;
import com.ph.security.zuul.hystrix.AuthServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ph-security-auth-server",fallback = AuthServiceHystrix.class)
public interface IAuthService {
}
