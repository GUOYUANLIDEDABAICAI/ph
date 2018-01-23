package com.ph.security.auth.biz.auth;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.ph.security.agent.entity.auth.PermissionInfo;
import com.ph.security.common.entity.User;
import com.ph.security.common.repository.UserRepository;
import com.ph.security.auth.service.GateService;
import com.ph.security.auth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

@Service
public class UserAuthBiz extends BaseBiz {

    @Autowired
    private UserRepository userRepository;


    @Value("${auth.api.tokenHead}")
    private String tokenHead;

    @Autowired
    public UserAuthBiz(
            JwtTokenUtil jwtTokenUtil,
            GateService gateService) {
        super(jwtTokenUtil,gateService);
    }

    public String login(String username, String password, HttpServletResponse response) {
        User user = userRepository.findByUsername(username);
        String token = null;
        logger.info("password编码后的值==========="+encoder.encode(password));
         if(encoder.matches(password,user.getPassword())) {
            token = jwtTokenUtil.generateToken(user);
         }
        return token;
    }

    public Boolean validate(String oldToken,String resource) {
        if(!oldToken.startsWith(tokenHead))
            return false;
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getClientIdOrUsernameFromToken(token);
        return !jwtTokenUtil.isTokenExpired(token)&&validateResource(username,resource);
    }

    @Override
    public Boolean validateResource(String username, String resource){
        String [] res = resource.split(":");
        final String requestUri = res[0];
        final String method = res[1];
        List<PermissionInfo> userPermissionInfo = gateService.getUserPermissionInfo(username);
        Collection<PermissionInfo> result = Collections2.filter(userPermissionInfo, new Predicate<PermissionInfo>() {
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
