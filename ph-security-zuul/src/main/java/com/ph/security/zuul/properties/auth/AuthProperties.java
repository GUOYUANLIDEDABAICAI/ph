package com.ph.security.zuul.properties.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class AuthProperties {

    private String loginPage = "/login";

    private String loginInstance = "ph-security-auth-server";

    private String homePage = "index";

    private String ignorePre;

    private String authInstance = "ph-security-auth-server";

    private TokenProperties token = new TokenProperties();

    public String getAuthInstance() {
        return authInstance;
    }

    public void setAuthInstance(String authInstance) {
        this.authInstance = authInstance;
    }

    public TokenProperties getToken() {
        return token;
    }

    public void setToken(TokenProperties token) {
        this.token = token;
    }

    public String getIgnorePre() {
        return ignorePre;
    }

    public void setIgnorePre(String ignorePre) {
        this.ignorePre = ignorePre;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getLoginInstance() {
        return loginInstance;
    }

    public void setLoginInstance(String loginInstance) {
        this.loginInstance = loginInstance;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

}
