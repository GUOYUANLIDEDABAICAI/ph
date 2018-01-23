package com.ph.security.auth.biz;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.ph.security.common.entity.ClientInfo;
import com.ph.security.agent.entity.auth.PermissionInfo;
import com.ph.security.auth.service.GateService;
import com.ph.security.auth.util.JwtTokenUtil;
import com.ph.security.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AuthBiz {

    Logger logger = LoggerFactory.getLogger(getClass());
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private JwtTokenUtil jwtTokenUtil;
    private GateService gateService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Value("${auth.api.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthBiz(
            JwtTokenUtil jwtTokenUtil,
            GateService gateService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.gateService = gateService;
    }


    public String ClientLogin(String clientId, String secret) {
        ClientInfo info = gateService.getGateClientInfo(clientId);
        String token = "";
        //logger.info("123的加密后的值:"+encoder.encode("123"));
        //logger.info("test的加密后的值:"+encoder.encode("test"));
        if(encoder.matches(secret,info.getSecret())) {
           token = jwtTokenUtil.generateToken(info);
        }
        return token;
    }

    public String userLogin(String username) {
        User user = new User();
        user.setUsername(username);
        String token = "";
       // if(encoder.matches(secret,info.getSecret())) {
            token = jwtTokenUtil.generateToken(user);
       // }
        return token;
    }

    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String clientId = jwtTokenUtil.getClientIdOrUsernameFromToken(token);
        ClientInfo info = gateService.getGateClientInfo(clientId);
        if (jwtTokenUtil.canTokenBeRefreshed(token,info.getUpdTime())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    public Boolean validate(String oldToken,String resource) {
        if(!oldToken.startsWith(tokenHead))
            return false;
        final String token = oldToken.substring(tokenHead.length());
        String clientId = jwtTokenUtil.getClientIdOrUsernameFromToken(token);
        ClientInfo info = gateService.getGateClientInfo(clientId);
        //return info.getCode().equals(clientId)&&!jwtTokenUtil.isTokenExpired(token)&&validateResource(clientId,resource);
        return info.getCode().equals(clientId)&&validateResource(clientId,resource);
    }
    public Boolean validateUserToken(String oldToken,String resource) {
        if(!oldToken.startsWith(tokenHead))
            return false;
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getClientIdOrUsernameFromToken(token);
        return !jwtTokenUtil.isTokenExpired(token);
    }

    public Boolean validateResource(String clientId, String resource){
        String [] res = resource.split(":");
        final String requestUri = res[0];
        final String method = res[1];
        List<PermissionInfo> clientPermissionInfo = gateService.getGateServiceInfo(clientId);
        Collection<PermissionInfo> result = Collections2.filter(clientPermissionInfo, new Predicate<PermissionInfo>() {
            @Override
            public boolean apply(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                logger.info("uri================="+uri);
                //String regEx = "^" + uri + "$";
                logger.info(pathMatcher.match(uri,requestUri)+"");
                //return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
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
