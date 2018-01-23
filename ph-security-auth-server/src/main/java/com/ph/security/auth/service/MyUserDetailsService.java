/**
 * 
 */
package com.ph.security.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author hasse
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登录用户名:" + username);
		//这里的user需要重写继承UserDetails
		return new User(username, passwordEncoder.encode("12345"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		//return buildUser(username);
	}

	/*private SocialUserDetails buildUser(String userId) {
		//encode加密实际上是放在注册的时候完成的
		String password = passwordEncoder.encode("12345");
		logger.info("用户密码是:"+password);
		return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("登录用户名:" + userId);
		return buildUser(userId);
	}*/
}
