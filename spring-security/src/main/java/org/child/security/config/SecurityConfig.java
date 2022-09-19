package org.child.security.config;

import org.child.security.filter.SecurityFilter;
import org.child.security.handler.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/09
 */
// @Configuration(proxyBeanMethods = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthSuccessHandler authSuccessHandler() {
        return new AuthSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        // todo: 如果不配置 userDetailsService 默认使用什么?
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // super.configure(web);
    }

    // 认证管理器
    // @Bean
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    //     return super.authenticationManagerBean();
    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.csrf()
    //             // 关闭 csrf
    //             .disable()
    //             // 登陆页面配置
    //             // loginProcessingUrl: 登陆页面的 action 路径 -- 即后端接口
    //             // 登陆(认证)成功之后的 handler
    //             .formLogin(flc -> flc.loginProcessingUrl("/user/login")/*.loginPage("/user/login")*/
    //                     .successHandler(authSuccessHandler())
    //             )
    //             // 不通过 session 获取 SecurityContext
    //             .sessionManagement()
    //             // 无状态: 禁用 session
    //             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //             .and()
    //             .authorizeRequests()
    //             // 登陆接口允许匿名访问
    //             // anonymous 和 permitAll 区别?
    //             // anonymous: 允许匿名用户访问,不允许已登入用户访问
    //             // permitAll: 不管登入,不登入 都能访问
    //             .antMatchers("/user/login", "/login/**", "/favicon.ico")
    //             .anonymous()
    //             // 其他接口必须认证之后
    //             .anyRequest()
    //             .authenticated();
    //             // .and()
    //             // .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    // }

}
