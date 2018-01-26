package com.ph.security.demo.client.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.common.context.BaseContextHandler;
import com.ph.security.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    EurekaClient discoveryClient;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String login(){
        return "page/login/login";
    }

    @IgnoreAuthSecurity
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        User user = BaseContextHandler.getUser();
        model.addAttribute("user",user);
        return "index";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String mainPage(HttpServletResponse response, HttpServletRequest request){
        return "page/main";
    }
    @RequestMapping(value = "user",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String user(){
        return "page/user/list";
    }

    @RequestMapping(value = "user/edit",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String editUser(){
        return "page/user/edit";
    }

    @RequestMapping(value = "menu",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String getMenus(){
        return "page/menu/list";
    }

    @RequestMapping(value = "menu/edit",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String editMenu(){
        return "page/menu/edit";
    }

    @RequestMapping(value = "role",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String getRoles(){
        return "page/role/list";
    }

    @RequestMapping(value = "role/edit",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    public String editRole(){
        return "page/role/edit";
    }
}
