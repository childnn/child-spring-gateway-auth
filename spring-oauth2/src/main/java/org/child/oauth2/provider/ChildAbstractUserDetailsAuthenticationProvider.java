package org.child.oauth2.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 */
@Component
public class ChildAbstractUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    public static final Map<String, String> USERS = new HashMap<String, String>() {{
        put("user1", "1resu");
        put("child", "miaowan");
    }};
    @Autowired
    private UserDetailsService userDetailsService;

    public ChildAbstractUserDetailsAuthenticationProvider() {
        this.hideUserNotFoundExceptions = false;
    }

    // 校验
    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(
                    this.messages.getMessage("PhoneAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String username = authentication.getPrincipal().toString();
            String pwd = authentication.getCredentials().toString();

            // 根据用户名密码获取用户信息

            if (USERS.get(username) == null || !Objects.equals(USERS.get(username), pwd)) {
                throw new BadCredentialsException("用户名或密码错误");
            }

            // 调用登陆逻辑
            // ResponseMessage<Boolean> responseMessage = yongHuGlApi.login(principal, credentials);

            // if (responseMessage.runIsFailure()) {
            //     this.logger.debug("Authentication failed: password does not match stored value");
            //     throw new BadCredentialsException(this.messages.getMessage(responseMessage.getReturnCode().toString(), responseMessage.getReturnMessage()));
            // }
        }
    }

    @Override
    protected UserDetails retrieveUser(
            String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        UserDetails loadedUser;
        try {
            loadedUser = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException var6) {
            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }

        if (loadedUser == null) {
            throw new UsernameNotFoundException(
                    "UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }

    // @Override
    // public boolean isHideUserNotFoundExceptions() {
    //     return false;
    // }

    //
    // public void setUserDetailsService(UserDetailsService userDetailsService) {
    //     this.userDetailsService = userDetailsService;
    // }
    //
    // protected UserDetailsService getUserDetailsService() {
    //     return userDetailsService;
    // }

}
