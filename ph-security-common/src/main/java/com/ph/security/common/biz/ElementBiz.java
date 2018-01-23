package com.ph.security.common.biz;

import com.ph.security.common.entity.Element;
import com.ph.security.common.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementBiz {

    @Autowired
    private ElementRepository elementRepository;

    public List<Element> getAuthorityElementByUserId(String userId){
       return elementRepository.selectAuthorityElementByUserId(userId);
    }
    public List<Element> getAuthorityElementByUserId(String userId,String menuId){
        return elementRepository.selectAuthorityMenuElementByUserId(userId,menuId);
    }

    public List<Element> selectByExample(Element element) {
        Example<Element> example = Example.of(element);
        return elementRepository.findAll(example);
    }
}
