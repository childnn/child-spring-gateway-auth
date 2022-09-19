package org.child.oauth2.service;

import org.child.oauth2.provider.ChildAbstractUserDetailsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 */
@Service
public class ChildUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    static class SysUser {
        private final String username;
        private final String pwd;

        public SysUser(String username, String pwd) {
            this.username = username;
            this.pwd = pwd;
        }

        public String getUsername() {
            return username;
        }

        public String getPwd() {
            return pwd;
        }

    }

    /**
     * @see org.springframework.security.crypto.factory.PasswordEncoderFactories#createDelegatingPasswordEncoder()
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户信息
        SysUser sysUser = getUser(username);

        return new User(username, passwordEncoder.encode(sysUser.getPwd()), AuthorityUtils.NO_AUTHORITIES);
    }

    private SysUser getUser(String username) {
        return new SysUser(username, ChildAbstractUserDetailsAuthenticationProvider.USERS.get(username));
    }

}
