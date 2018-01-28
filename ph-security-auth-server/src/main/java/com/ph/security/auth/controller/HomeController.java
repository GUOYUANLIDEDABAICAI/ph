package com.ph.security.auth.controller;

import com.ph.security.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentUser(){
        Object obj = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (!(obj instanceof String)){
            UserDetails userDetails = (UserDetails) obj;
            return userDetails.getUsername();
        } else{
            return "jjjjj";
        }

    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletResponse response, HttpServletRequest request){

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:"+redirectUrl);
        }

        return "login";
    }
}
