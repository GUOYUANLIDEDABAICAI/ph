package com.ph.security.auth.security;

import java.io.Serializable;

public class ClientAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String clientId;
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }



    public ClientAuthenticationRequest() {
        super();
    }

    public ClientAuthenticationRequest(String clientId, String secret) {
        this.setClientId(clientId);
        this.setSecret(secret);
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
