package com.longlysmile.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * TODO
 *
 * @author wujie
 * @version 1.0
 * @date 2020/10/25 22:35
 */
public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}