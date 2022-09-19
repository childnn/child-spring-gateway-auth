package org.child.oauth2.filter;

import org.child.oauth2.handler.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
 * @see org.springframework.security.authentication.UsernamePasswordAuthenticationToken
 * @since 2022/09/06
 */
@Component
public class ChildUserAuthFilter extends UsernamePasswordAuthenticationFilter/*AbstractAuthenticationProcessingFilter*/ {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ChildUserAuthFilter() {
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public void afterPropertiesSet() {
        setAuthenticationSuccessHandler(authSuccessHandler);
        setAuthenticationManager(authenticationManager);
        super.afterPropertiesSet();
    }

    // @Autowired
    // @Override
    // public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
    //     super.setAuthenticationSuccessHandler(successHandler);
    // }

    // public AuthSuccessHandler getAuthSuccessHandler() {
    //     return authSuccessHandler;
    // }

}
