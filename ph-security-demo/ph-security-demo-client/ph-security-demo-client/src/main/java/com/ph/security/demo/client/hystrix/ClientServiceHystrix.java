package com.ph.security.demo.client.hystrix;

import com.ph.security.demo.client.rpc.IClientService;
import org.springframework.stereotype.Component;

@Component
public class ClientServiceHystrix implements IClientService {
    public String clientAuthen() {
        return "客户端访问失败";
    }
}
