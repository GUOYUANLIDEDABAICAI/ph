package com.ph.security.zuul.properties;

import com.ph.security.zuul.properties.auth.AuthProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ph.zuul")
@Component
public class ZuulProperties {

    private AuthProperties auth = new AuthProperties();

    private String zuulInstance;

    public String getZuulInstance() {
        return zuulInstance;
    }

    public void setZuulInstance(String zuulInstance) {
        this.zuulInstance = zuulInstance;
    }

    public AuthProperties getAuth() {
        return auth;
    }

    public void setAuth(AuthProperties auth) {
        this.auth = auth;
    }
}
