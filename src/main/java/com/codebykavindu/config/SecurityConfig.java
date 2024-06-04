package com.codebykavindu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Kavindu Perera
 */
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(//here I say what to and how to secure.
                        authorizeHttp -> {
                            authorizeHttp.requestMatchers("/").permitAll();
                            authorizeHttp.requestMatchers("/favicon.svg").permitAll();
                            authorizeHttp.requestMatchers("/css/*").permitAll();
                            authorizeHttp.requestMatchers("/error").permitAll();
                            authorizeHttp.anyRequest().authenticated(); // any request should be authenticated except the above.
                        }
                )
                .formLogin(l -> l.defaultSuccessUrl("/private")) // here I say how I need to log in.
                .logout(l -> l.logoutSuccessUrl("/"))
                .oauth2Login(withDefaults()) // I want to enable OAuth log in as well.
                .addFilterBefore(new TheNewFilter(), AuthorizationFilter.class) // Add a filter before AuthorizationFilter.
                .addFilterBefore(new RobotAuthenticatioFilter(), AuthorizationFilter.class)
                .authenticationProvider(new KavinduAuthenticationProvider())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("user")
                        .build()
        );
    }
}
