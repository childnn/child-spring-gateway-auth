package org.child.security.service;

import org.child.security.entity.LoginUser;
import org.child.security.entity.User;
import org.child.security.util.CacheUtil;
import org.child.security.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/09
 */
@Service
public class LoginService {

    // @Autowired
    private AuthenticationManager authenticationManager;


    public String login(User user) {
        // 此构造方法, setAuthenticated(false); 表示为认证状态
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // AuthenticationManager 会转到
        // org.springframework.security.core.userdetails.UserDetailsService.loadUserByUsername
        //  -> org.springframework.security.authentication.ProviderManager.authenticate
        //   -> org.springframework.security.authentication.AuthenticationProvider.authenticate
        Authentication authenticate = authenticationManager.authenticate(authentication);
        // 校验失败
        if (authenticate == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 校验通过: 这里返回的 principal 就是
        // org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername 方法的返回值
        LoginUser loginUser = ((LoginUser) authenticate.getPrincipal());
        String username = loginUser.getUsername();

        // 登录成功, 缓存登录状态
        // org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider.setUserCache
        CacheUtil.cache(username, loginUser);

        // 获取 token
        return JwtUtil.getToken(new HashMap<String, String>() {{
            put("username", username);
        }});
    }

}
