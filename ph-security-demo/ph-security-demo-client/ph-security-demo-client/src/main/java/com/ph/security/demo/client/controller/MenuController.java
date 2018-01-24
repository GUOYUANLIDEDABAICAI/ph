package com.ph.security.demo.client.controller;

import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.common.biz.MenuBiz;
import com.ph.security.common.biz.UserBiz;
import com.ph.security.common.context.BaseContextHandler;
import com.ph.security.common.entity.Menu;
import com.ph.security.common.vo.AuthorityMenuTree;
import com.ph.security.common.vo.MenuTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-12 8:49
 */
@Controller
public class MenuController{
    @Autowired
    private UserBiz userBiz;

    @Autowired
    private MenuBiz menuBiz;
/*
    @RequestMapping(value = "/user/menu",method = RequestMethod.GET)
    @ResponseBody
    @NeverAuthSecurity
    public String getUserMenu(@RequestParam(defaultValue = "13") Integer parentId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return userBiz.getMenusByUsername(BaseContextHandler.getName(),parentId);
    }*/

    @RequestMapping(value = "admin//user/system",method = RequestMethod.GET)
    @ResponseBody
    @IgnoreAuthSecurity
    public String getUserSystem(@RequestParam(defaultValue = "13") Integer parentId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return userBiz.getSystemsByUsername(BaseContextHandler.getUser().getUsername(),parentId);
    }

    @RequestMapping(value = "menu/list",method = RequestMethod.GET)
    @IgnoreAuthSecurity
    @ResponseBody
    public List<Menu> getMenus(){
        return menuBiz.getMenus();
    }

}
