package com.ph.security.demo.client.hystrix;

import com.ph.security.demo.client.rpc.ILanguageService;
import org.springframework.stereotype.Component;

@Component
public class LanguageServiceHystrix implements ILanguageService {
    public String clientAuthen() {
        return "客户端访问失败";
    }
}
