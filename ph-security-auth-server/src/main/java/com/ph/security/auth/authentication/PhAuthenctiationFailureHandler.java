/**
 * 
 */
package com.ph.security.auth.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author hasse
 *
 */
@Component("phAuthenctiationFailureHandler")
public class PhAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler{


	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		log.info("登录失败");
		//if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
		//response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        /*} else {
            super.onAuthenticationFailure(request, response, exception);
        }
*/
	}
}
