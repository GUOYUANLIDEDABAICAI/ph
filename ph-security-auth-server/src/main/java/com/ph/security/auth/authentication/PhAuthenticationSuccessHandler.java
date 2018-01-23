/**
 * 
 */
package com.ph.security.auth.authentication;

import com.ph.security.auth.biz.AuthBiz;
import com.ph.security.auth.biz.auth.UserAuthBiz;
import com.ph.security.auth.properties.GeneralProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hasse
 *
 */
@Component("phAuthenticationSuccessHandler")
public class PhAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GeneralProperties generalProperties;

	/*@Autowired
	AuthBiz authBiz;*/

	@Autowired
	UserAuthBiz userAuthBiz;

	@Autowired
	Jedis jedis;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("登录成功");
		/*logger.info(authentication.getName());
		String token = userAuthBiz.login(authentication.getName());
        jedis.set("token",token);
        jedis.expire("token",70);*/
		/*String redirectUrl = (String) jedis.get(request.getRemoteHost()+"redirectUrl");
		logger.info("缓存的从定向页面是:"+redirectUrl);
		logger.info("key值是:"+request.getRemoteHost()+"redirectUrl");
		if (redirectUrl != null){
		    jedis.del(request.getRemoteAddr()+"redirectUrl");
			response.sendRedirect(redirectUrl);
		}else {
			response.sendRedirect(generalProperties.getAuth().getHomePage());
		}*/


	}

}
