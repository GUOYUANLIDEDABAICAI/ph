package com.ph.security.demo.client.rest;

import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.demo.client.future.LanguageFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

@Controller
public class SaysomethingRest {
    @Autowired
    private LanguageFuture languageFuture;
    @RequestMapping("test")
    @IgnoreAuthSecurity
    public @ResponseBody String test() throws ExecutionException, InterruptedException {
        return languageFuture.clientAuthen().get();
    }
}
