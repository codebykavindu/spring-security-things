package com.codebykavindu.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

/**
 * @author Kavindu Perera
 */
public class KavinduAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getName().equals("kavindu")) {
            //OK
            var kavindu = User.withUsername("kavindu")
                    .password("~~~ignored~~~")
                    .roles("user", "admin")
                    .build();

            return UsernamePasswordAuthenticationToken.authenticated(
                    kavindu,
                    null,
                    kavindu.getAuthorities()
            );
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
