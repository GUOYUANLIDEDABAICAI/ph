package com.ph.security.demo.client.controller;

import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.common.biz.RoleBiz;
import com.ph.security.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleBiz roleBiz;

    @RequestMapping(value = "role/list",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    @ResponseBody
    public List<Role> getMenus(){
        return roleBiz.getRoles();
    }
}
