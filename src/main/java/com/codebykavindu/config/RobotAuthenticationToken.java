package com.codebykavindu.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * @author Kavindu Perera
 */
class RobotAuthenticationToken extends AbstractAuthenticationToken {

    public RobotAuthenticationToken() {
        super(AuthorityUtils.createAuthorityList("ROLE_robot"));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return "Ms Robot";
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new RuntimeException("YOU CAN'T TOUCH THIS");
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
