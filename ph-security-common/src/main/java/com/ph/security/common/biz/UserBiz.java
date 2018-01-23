package com.ph.security.common.biz;

import com.alibaba.fastjson.JSONObject;
import com.ph.security.common.entity.Element;
import com.ph.security.common.entity.Menu;
import com.ph.security.common.entity.Role;
import com.ph.security.common.entity.User;
import com.ph.security.common.repository.ElementRepository;
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

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.awt.SystemColor.info;
import static java.awt.SystemColor.menu;

@Service
public class UserBiz {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Menu> getMenusByUsername(String username) {
        logger.info("获取菜单的用户是:"+username);
        User user = findByUsername(username);
        List<Element> elements = new ArrayList<Element>();
        Set<Element> elementSet = new HashSet<Element>();
        for (Role g: user.getRoles()
                ) {
            for (Element e:g.getElements()
                    ) {
                if(elementSet.add(e)){
                    elements.add(e);
                }
            }
        }
       List<Menu> menus = menuConvert(elements);
        return menus;
        //     return JSONObject.toJSONString(getMenuTree(menus,parentId));
        
    }

    private List<Menu> menuConvert(List<Element> elements) {
        Set<String> idSet = new HashSet<>();
        List<Menu> ids = new ArrayList<>();
        for (Element e:elements
             ) {
            if (idSet.add(e.getMenuId())){
                Menu menu = menuRepository.findOne(Integer.valueOf(e.getMenuId()));
                if (menu != null)
                    ids.add(menu);
            }
        }

        return ids;
    }

    private List<MenuTree> getMenuTree(List<Menu> menus,int root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            if (node.getHref() != null && node.getHref().length() >= 2){
                node.setHref(node.getHref().substring(1));
            }
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root) ;
    }

    public String getSystemsByUsername(String name, Integer parentId) {
        Menu menu = new Menu();
        menu.setParentId(parentId);
        Example<Menu> example = Example.of(menu);
        List<Menu> sysMenus = menuRepository.findAll(example);
        List<Menu> menusList = getMenusByUsername(name);
        List<Menu> menus = new ArrayList<>();
        for (Menu m: sysMenus
                ) {
            menus.add(m);

        }
        if (menusList != null){
            for (Menu m1:menusList
                    ) {
                menus.add(m1);
            }
        }

        return JSONObject.toJSONString(getMenuTree(menus,parentId));
    }

    public String getUserList() {
        List<User> users = userRepository.findAll();
        List<UserVo> userVos = userConvert(users);
        return JSONObject.toJSONString(userVos);
    }

    private List<UserVo> userConvert(List<User> users) {
        ArrayList<UserVo> userVos = new ArrayList<>();
        for (User user: users
             ) {
            UserVo userVo = new UserVo();
            userVo.setUserName(user.getUsername());
            userVo.setUserEmail(user.getEmail());
            userVo.setUserId(user.getId());
            userVo.setUserGrade("普通会员");
            userVo.setUserEndTime("2017");
            userVo.setUserStatus(user.getStatus());
            userVo.setUserSex(user.getSex());
            userVos.add(userVo);
        }
        return userVos;
    }

    public void addUser(UserVo userVo){
        User user = new User();
        user.setUsername(userVo.getUserName());
        user.setName(userVo.getName());
        user.setPassword(userVo.getUserPassword());
        user.setSex(userVo.getUserSex());
        user.setEmail(userVo.getUserEmail());
        user.setStatus("0");
        userRepository.save(user);
    }

    public List<Element> selectByExample(Example example) {
        return elementRepository.findAll(example);
    }
}
