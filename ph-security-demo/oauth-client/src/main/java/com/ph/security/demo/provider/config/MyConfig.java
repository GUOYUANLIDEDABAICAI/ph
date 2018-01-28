package com.ph.security.demo.provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@EnableOAuth2Client
@Configuration
public class MyConfig{

    @Value("${oauth.resource:http://localhost:8603}")
    private String baseUrl;
    @Value("${oauth.authorize:http://localhost:8601/oauth/authorize}")
    private String authorizeUrl;
    @Value("${oauth.token:http://localhost:8601/login}")
    private String tokenUrl;


    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();

        List scopes = new ArrayList<String>(1);
        scopes.add("all");
        //scopes.add("read");
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId("client");
        resource.setClientSecret("secret");
        resource.setGrantType("password");
        resource.setId("");
        resource.setScope(scopes);

        resourceDetails.setAccessTokenUri(tokenUrl);
        resourceDetails.setUserAuthorizationUri(authorizeUrl);
        resourceDetails.setClientId("client");
        resourceDetails.setClientSecret("secret");
        resourceDetails.setGrantType("authorization_code");
        resourceDetails.setScope(scopes);

        resource.setUsername("user");
        resource.setPassword("12345");

        return resource;
    }

    @Bean
    public OAuth2RestOperations oAuth2RestOperations() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
    }

}
