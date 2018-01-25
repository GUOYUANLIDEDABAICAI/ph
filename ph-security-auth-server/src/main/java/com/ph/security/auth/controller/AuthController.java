package com.ph.security.auth.controller;

import com.ph.security.auth.biz.auth.ClientAuthBiz;
import com.ph.security.auth.biz.auth.UserAuthBiz;
import com.ph.security.auth.security.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/authen")
public class AuthController {
    @Value("${auth.api.header}")
    private String tokenHeader;

    @Autowired
    private UserAuthBiz userAuthBiz;

    @Autowired
    private ClientAuthBiz clientAuthBiz;

    @ApiOperation(value="获取用户的token", notes="传入用户名和密码")
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public ResponseEntity<?> createUserAuthenticationToken(
            @RequestBody UserAuthenticationRequest authenticationRequest) {
        final String token = userAuthBiz.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @ApiOperation(value="获取服务客户端的token", notes="传入网关中心发布的客户端clientId和secret")
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody ClientAuthenticationRequest authenticationRequest) {
        final String token = clientAuthBiz.login(authenticationRequest.getClientId(), authenticationRequest.getSecret());

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @ApiOperation(value="刷新并获取新的token", notes="传入旧的token")
    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            String token) {
        String refreshedToken = userAuthBiz.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        }
    }

    @ApiOperation(value="验证客户端token是否有效", notes="传入申请的token")
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public ResponseEntity<?> verify(@ApiParam(value = "前缀+token,例如:PToken+token" ,required=true ) @RequestParam String token,
                                    @ApiParam(value = "资源路径+请求方式,例如:/provider/test:GET",required = true) @RequestParam String resource){
        if(clientAuthBiz.validate(token,resource))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.status(401).body(false);
    }

    @ApiOperation(value="验证用户的token是否有效", notes="传入申请的token")
    @RequestMapping(value = "userVerify", method = RequestMethod.GET)
    public ResponseEntity<?> verifyUserToken(@ApiParam(value = "前缀+token,例如:PToken+token",required = true) @RequestParam String token,
                                             @ApiParam(value = "资源路径+请求方式,例如:/index:GET",required = true) @RequestParam String resource){
        if(userAuthBiz.validate(token,resource))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.status(401).body(false);
    }

}
