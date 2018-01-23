package com.ph.security.auth.util;

import com.ph.security.common.entity.User;
import com.ph.security.common.entity.ClientInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${auth.api.secret}")
    private String secret;

    @Value("${auth.api.expiration}")
    private Long expiration;

    public String getClientIdOrUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = (String) claims.get("sub");
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        logger.info("过期时间参数======"+expiration);
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        logger.info("token过期时间===================:"+expiration);
        if (expiration == null){
            return true;
        }
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(Object obj) {
        if (obj instanceof ClientInfo){
            ClientInfo info = (ClientInfo) obj;
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put(CLAIM_KEY_USERNAME, info.getCode());
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        } else {

            User user = (User) obj;
            Map<String, Object> claims = new HashMap<String, Object>();
            claims.put(CLAIM_KEY_USERNAME, user.getUsername());
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateUserToken(claims);
        }

    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    String generateUserToken(Map<String, Object> claims) {
        logger.info("设定的过期时间====="+generateExpirationDate());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, ClientInfo info) {
        final String clientId = getClientIdOrUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        return (
                clientId.equals(info.getCode())
                        && !isTokenExpired(token));
    }

}

