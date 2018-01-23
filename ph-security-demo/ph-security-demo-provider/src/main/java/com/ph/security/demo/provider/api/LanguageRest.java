package com.ph.security.demo.provider.api;

import com.ph.security.agent.annotation.ClientSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ClientSecurity
@RequestMapping("provider")
public class LanguageRest {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public @ResponseBody String clientAuthen() throws InterruptedException {
        return "客户端访问成功";
    }
}
