package org.child.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 */
@EnableWebFluxSecurity
public class WebSecurityConfig {

    // @Autowired
    // private AuthenticationManager authenticationManager;
    // private WebSecurityConfigurerAdapter webSecurityConfigurerAdapter;
    @Autowired
    private ReactiveAuthenticationManager reactiveAuthenticationManager;

    @Autowired
    private ServerSecurityContextRepository securityContextRepository;

    // @Bean
    // ReactiveAuthenticationManager reactiveAuthenticationManager() {
    //     return new GatewayAuthenticationManager();
    // }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/child-auth/**", "/auth/login").permitAll()
                .anyExchange().authenticated().and().build();
    }

}
