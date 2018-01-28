package com.ph.security.auth.config;

import com.ph.security.auth.properties.GeneralProperties;
import com.ph.security.auth.configuration.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    private GeneralProperties generalProperties = new GeneralProperties();

    @Autowired
    private SavedRequestAwareAuthenticationSuccessHandler phAuthenticationSuccessHandler;

    @Autowired
    private SimpleUrlAuthenticationFailureHandler phAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(generalProperties.getAuth().getLoginPage())
                //.loginProcessingUrl(generalProperties.getAuth().getLoginProcessingUrl())
                .successHandler(phAuthenticationSuccessHandler)
                .failureHandler(phAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/api/authen/*","/swagger-ui.html","/v2/api-docs","/swagger-resources/**","/oauth/**",
                        generalProperties.getAuth().getLoginPage(),
                        generalProperties.getAuth().getLoginProcessingUrl())
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
