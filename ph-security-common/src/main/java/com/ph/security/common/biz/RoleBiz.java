package com.ph.security.common.biz;

import com.ph.security.common.entity.Element;
import com.ph.security.common.entity.Role;
import com.ph.security.common.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleBiz {

    @Autowired
    private RoleRepository roleRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public List<Element> getElementsById(Integer roleId) {
        Role role = roleRepository.findOne(roleId);
        return role.getElements();
    }
}
