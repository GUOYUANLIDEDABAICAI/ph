package com.ph.security.auth.properties;

import com.ph.security.auth.properties.auth.AuthProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ph.security")
@Component
public class GeneralProperties {

    private AuthProperties auth = new AuthProperties();

    public AuthProperties getAuth() {
        return auth;
    }

    public void setAuth(AuthProperties auth) {
        this.auth = auth;
    }
}
