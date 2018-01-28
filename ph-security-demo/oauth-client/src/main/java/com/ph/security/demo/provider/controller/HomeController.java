package com.ph.security.demo.provider.controller;

import com.alibaba.fastjson.JSONObject;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static com.netflix.config.DeploymentContext.ContextKey.appId;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OAuth2RestOperations oAuth2RestOperations;

    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public String getuser(@RequestParam(value = "code",defaultValue = "")String code){
        OAuth2AccessToken accessToken = oAuth2RestOperations.getAccessToken();
        logger.info(accessToken.getValue());

        /*String url = "http://localhost:8601/oauth/token";
        //入参
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id","client");
        jsonObject.put("client_secret","secret");
        jsonObject.put("grant_type","authorization_code");
        jsonObject.put("code",code);
        jsonObject.put("redirect_uri","http://localhost:8701/callback");

        HttpResponse response = HttpRequest.post(url).contentType("application/json;charset=utf-8").body(jsonObject.toJSONString())
                .send();

        logger.info("token================"+response);*/
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
