package org.child.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 */
@SpringBootApplication
public class AuthApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }

    /**
     * Authentication: token-认证信息
     *
     * @see org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     * AuthenticationProvider: 处理特定的 Authentication
     * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#supports(Class)
     * AuthenticationManager
     */
    void f() {
    }

}
