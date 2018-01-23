package com.ph.security.zuul.properties.auth;

public class TokenProperties {

    private String create_token_url = "/api/authen/token";

    public String getCreate_token_url() {
        return create_token_url;
    }

    public void setCreate_token_url(String create_token_url) {
        this.create_token_url = create_token_url;
    }
}
