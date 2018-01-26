package com.ph.security.demo.client.controller;

import com.alibaba.fastjson.JSON;
import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.common.biz.UserBiz;
import com.ph.security.common.entity.Element;
import com.ph.security.common.entity.User;
import com.ph.security.common.vo.PageResult;
import com.ph.security.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    @ResponseBody
    @IgnoreAuthSecurity
    public PageResult<User> getUserLists(@RequestParam(defaultValue = "10") int limit,
                                             @RequestParam(defaultValue = "1") int offset){
        List<User> users = userBiz.getUserList();
        return new PageResult<User>(users.size(), users);
    }

    /*@RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(HttpServletResponse response, @RequestBody UserVo userVo){
        response.setHeader("Access-Control-Allow-Origin","*");
        HashMap<String, Object> map = new HashMap<>();
        try {
            userBiz.addUser(userVo);
        } catch (Exception e) {
            map.put("msg","添加失败");
            return JSON.toJSONString(map);
        }
        map.put("msg","添加成功");
        return JSON.toJSONString(map);
    }*/
}
