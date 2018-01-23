package com.ph.security.demo.client.hystrix;

import com.ph.security.demo.client.rpc.ILanguageService;
import org.springframework.stereotype.Component;

@Component
public class LanguageServiceHystrix implements ILanguageService {
    public String sayChineseHelloWorld() {
        return "hystrix 哑巴！";
    }

    public String sayEnglishHelloWorld() {
        return "hystrix 哑巴！";
    }
}
