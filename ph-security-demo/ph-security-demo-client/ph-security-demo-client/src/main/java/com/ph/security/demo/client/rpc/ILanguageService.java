package com.ph.security.demo.client.rpc;

import com.ph.security.demo.client.hystrix.LanguageServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ph-security-demo-provider",fallback = LanguageServiceHystrix.class)
public interface ILanguageService {
    @RequestMapping(value = "/language/chinese",method = RequestMethod.GET)
    public String sayChineseHelloWorld();
    @RequestMapping(value = "/language/english",method = RequestMethod.GET)
    public String sayEnglishHelloWorld();
}
