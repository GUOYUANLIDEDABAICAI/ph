package com.ph.security.demo.client.rest;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.demo.client.future.ClientFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class AuthRest {
    @Autowired
    private ClientFuture clientFuture;

    @RequestMapping("test")
    @IgnoreAuthSecurity
    public @ResponseBody String test() throws ExecutionException, InterruptedException {
        return clientFuture.clientAuthen().get();
    }
    @RequestMapping("/swagger")
    @IgnoreAuthSecurity
    public void swaggerPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://172.18.110.115:8601/swagger-ui.html");
    }
}
