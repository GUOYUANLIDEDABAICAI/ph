package com.ph.security.common.biz;

import com.alibaba.fastjson.JSONObject;
import com.ph.security.common.entity.Element;
import com.ph.security.common.entity.Group;
import com.ph.security.common.entity.Menu;
import com.ph.security.common.entity.User;
import com.ph.security.common.repository.MenuRepository;
import com.ph.security.common.repository.UserRepository;
import com.ph.security.common.util.TreeUtil;
import com.ph.security.common.vo.MenuTree;
import com.ph.security.common.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuBiz {

    Logger logger = LoggerFactory.getLogger(getClass());



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }
}
