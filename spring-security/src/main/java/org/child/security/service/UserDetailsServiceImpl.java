package org.child.security.service;

import org.child.security.entity.LoginUser;
import org.child.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @see org.springframework.security.authentication.AuthenticationManager
 * @since 2022/09/09
 */
// @Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
     * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#retrieveUser(String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
     * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        User user = findByUsername(username);

        // todo: 查询权限: GrantedAuthority

        return new LoginUser(user);
    }

    // 目前定义, 无论用户名为何, 密码均为 admin
    private User findByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("admin"));
        return user;
    }

}
