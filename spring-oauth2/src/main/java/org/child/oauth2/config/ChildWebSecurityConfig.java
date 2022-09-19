package org.child.oauth2.config;

import org.child.oauth2.filter.ChildUserAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 */
@Configuration
public class ChildWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 默认 {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
     */
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private ChildUserAuthFilter childUserAuthFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * @see org.springframework.security.config.authentication.AuthenticationManagerFactoryBean#getObject()
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(childUserAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .and()
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/child-auth/**").permitAll()
                .anyRequest().authenticated();
    }

}
