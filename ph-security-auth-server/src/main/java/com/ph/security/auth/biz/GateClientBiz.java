package com.ph.security.auth.biz;


import com.ph.security.auth.constant.CommonConstant;
import com.ph.security.auth.constant.UserConstant;
import com.ph.security.common.entity.Element;
import com.ph.security.common.entity.AuthClient;
import com.ph.security.common.entity.AuthClientService;
import com.ph.security.common.repository.ClientRepository;
import com.ph.security.common.repository.ClientServiceRepostitory;
import com.ph.security.common.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GateClientBiz {
    @Autowired
    private ElementRepository elementRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientServiceRepostitory clientServiceRepostitory;

    public void insertSelective(AuthClient entity) {
        String secret = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getSecret());
        entity.setSecret(secret);
        entity.setLocked(CommonConstant.BOOLEAN_NUMBER_FALSE);
        clientRepository.save(entity);
    }

    public void updateById(AuthClient entity) {
        AuthClient old = clientRepository.findOne(entity.getId());
        if(!entity.getSecret().equals(old.getSecret())){
            String secret = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getSecret());
            entity.setSecret(secret);
        }
        clientRepository.updateById(entity.getSecret(),entity.getId());
    }

    public void modifyClientServices(int id, String services) {
        clientServiceRepostitory.delete(id);
        if(!StringUtils.isEmpty(services)){
            String[] mem = services.split(",");
            for(String m:mem){
                AuthClientService clientService = new AuthClientService();
                clientService.setServiceId(Integer.parseInt(m));
                clientService.setClientId(id);
                clientServiceRepostitory.save(clientService);
            }
        }
    }

    public List<Element> getClientServices(int id) {
       //return elementRepository.selectAuthorityElementByClientId(id+"");
        return null;
    }

    public List<AuthClient> selectByExample(AuthClient gateClient) {
        Example<AuthClient> example = Example.of(gateClient);
        return clientRepository.findAll(example);
    }
}
