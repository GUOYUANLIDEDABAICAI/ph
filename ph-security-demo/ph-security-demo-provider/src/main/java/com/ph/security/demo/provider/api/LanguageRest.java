package com.ph.security.demo.provider.api;

import com.ph.security.agent.annotation.ClientSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ClientSecurity
@RequestMapping("language")
public class LanguageRest {
    @RequestMapping(value = "/chinese",method = RequestMethod.GET)
    public @ResponseBody String sayChineseHelloWorld() throws InterruptedException {
        return "你好，世界！";
    }
    @RequestMapping(value = "/english",method = RequestMethod.GET)
    public @ResponseBody String sayEnglishHelloWorld(){
        return "Hello World！";
    }
}
