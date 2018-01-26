package com.ph.security.demo.client.controller;

import com.ph.security.agent.annotation.IgnoreAuthSecurity;
import com.ph.security.common.biz.RoleBiz;
import com.ph.security.common.biz.UserBiz;
import com.ph.security.common.entity.Element;
import com.ph.security.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "element")
public class ElementController {
    @Autowired
    private UserBiz userBiz;

    @Autowired
    private RoleBiz roleBiz;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    @IgnoreAuthSecurity
    public PageResult<Element> page(@RequestParam(defaultValue = "10") int limit,
                                    @RequestParam(defaultValue = "1") int offset, String name, @RequestParam(defaultValue = "0") int menuId,@RequestParam(required = false) Integer roleId) {
        List<Element> elements = new ArrayList<>();
        if (roleId == null) {
            Element element = new Element();
            element.setMenuId(menuId + "");
            Example example = Example.of(element);
            elements = userBiz.selectByExample(example);
        }else {
            elements = roleBiz.getElementsById(roleId);
        }
        return new PageResult<Element>(elements.size(), elements);
    }
}
