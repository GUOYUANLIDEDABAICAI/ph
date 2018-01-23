package com.ph.security.auth.biz.auth;

import com.ph.security.auth.service.GateService;
import com.ph.security.auth.util.JwtTokenUtil;
import com.ph.security.common.entity.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthBiz extends BaseBiz{

    @Value("${auth.api.tokenHead}")
    private String tokenHead;

    @Autowired
    public ClientAuthBiz(
            JwtTokenUtil jwtTokenUtil,
            GateService gateService) {
        super(jwtTokenUtil,gateService);
    }

    public String login(String clientId, String secret) {
        ClientInfo info = gateService.getGateClientInfo(clientId);
        String token = "";
        //logger.info("123的加密后的值:"+encoder.encode("123"));
        //logger.info("test的加密后的值:"+encoder.encode("test"));
        if(encoder.matches(secret,info.getSecret())) {
            token = jwtTokenUtil.generateToken(info);
        }
        return token;
    }

    public Boolean validate(String oldToken,String resource) {
        logger.info(oldToken);
        if(!oldToken.startsWith(tokenHead))
            return false;
        final String token = oldToken.substring(tokenHead.length());
        String clientId = jwtTokenUtil.getClientIdOrUsernameFromToken(token);
        ClientInfo info = gateService.getGateClientInfo(clientId);
        return info.getCode().equals(clientId)&&!jwtTokenUtil.isTokenExpired(token)&&validateResource(clientId,resource);
        //return info.getCode().equals(clientId)&&validateResource(clientId,resource);
    }

}
