package com.ph.security.auth.biz.auth;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.ph.security.agent.entity.auth.PermissionInfo;
import com.ph.security.auth.service.GateService;
import com.ph.security.auth.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

public class BaseBiz {

    Logger logger = LoggerFactory.getLogger(getClass());
    AntPathMatcher pathMatcher = new AntPathMatcher();

    JwtTokenUtil jwtTokenUtil;
    GateService gateService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public BaseBiz(
            JwtTokenUtil jwtTokenUtil,
            GateService gateService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.gateService = gateService;
    }


    public Boolean validateResource(String name, String resource){
        String [] res = resource.split(":");
        final String requestUri = res[0];
        final String method = res[1];
        List<PermissionInfo> clientPermissionInfo = gateService.getGateServiceInfo(name);
        Collection<PermissionInfo> result = Collections2.filter(clientPermissionInfo, new Predicate<PermissionInfo>() {
            @Override
            public boolean apply(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                logger.info("uri================="+uri);
                logger.info(pathMatcher.match(uri,requestUri)+"");
                return pathMatcher.match(uri,requestUri)
                        && method.equals(permissionInfo.getMethod());
            }
        });
        if (result.size() <= 0) {
            return false;
        }
        return true;
    }
}
