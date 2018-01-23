/**
 * 
 */
package com.ph.security.auth.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ph.security.auth.properties.GeneralProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author hasse
 *
 */
@Component("phAuthenctiationFailureHandler")
public class PhAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GeneralProperties generalProperties;

	@Autowired
	Jedis jedis;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.info("登录失败");
		/*String redirectUrl = (String) jedis.get(request.getRemoteHost()+"redirectUrl");
		logger.info("缓存的从定向页面是:"+redirectUrl);
		logger.info("key值是:"+request.getRemoteHost()+"redirectUrl");
		if (redirectUrl != null){
			//jedis.del(request.getRemoteAddr()+"redirectUrl");
			response.sendRedirect(redirectUrl);
		}else {
			response.sendRedirect(generalProperties.getAuth().getHomePage());
		}*/
	}
}
