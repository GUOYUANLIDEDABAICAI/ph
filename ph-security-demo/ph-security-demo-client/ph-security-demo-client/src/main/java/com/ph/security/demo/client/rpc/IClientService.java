package com.ph.security.demo.client.rpc;

import com.ph.security.demo.client.hystrix.ClientServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ph-security-demo-provider",fallback = ClientServiceHystrix.class)
public interface IClientService {
    @RequestMapping(value = "/provider/test",method = RequestMethod.GET)
    public String clientAuthen();
}
