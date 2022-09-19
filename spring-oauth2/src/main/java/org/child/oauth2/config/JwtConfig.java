package org.child.oauth2.config;

import org.child.oauth2.converter.CustomerAccessTokenConverter;
import org.child.oauth2.jwt.JwtTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/07
 */
@Configuration
public class JwtConfig {

    /**
     * 将授权码存放在jwtToken
     *
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     * @author wanglufei
     * @date 2022/4/12 3:55 PM
     */
    @Bean
    TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwtToken和AccessToken的相互转换
     *
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     * @author wanglufei
     * @date 2022/4/12 3:54 PM
     */
    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //添加认证JwtToken的密钥
        jwtAccessTokenConverter.setSigningKey("test_key");
        jwtAccessTokenConverter.setAccessTokenConverter(new CustomerAccessTokenConverter());
        return jwtAccessTokenConverter;
    }

    /**
     * @return com.uin.config.JwtTokenEnhancer
     * @author wanglufei
     * @date 2022/4/12 4:27 PM
     */
    @Bean
    JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

}
