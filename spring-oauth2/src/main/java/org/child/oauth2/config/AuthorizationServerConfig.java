package org.child.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.List;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 * 资源服务
 * 授权服务:
 * AuthorizationServerEndpointsConfiguration:
 * - AuthorizationEndpoint: 授权请求入口
 * - TokenEndpoint: 生成 token 入口
 * - AuthorizationServerTokenServices
 * - TokenKeyEndpointRegistrar
 */
@Configuration
@EnableAuthorizationServer // 启用授权服务
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${jwtTokenPassword}")
    public String jwtTokenPassword;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    // @Autowired
    // private JwtTokenEnhancer jwtTokenEnhancer;

    // @Autowired
    // private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private List<TokenEnhancer> tokenEnhancers;

    @Autowired
    private TokenStore jwtTokenStore;

    // todo: 作用为何?
    @Bean
    InMemoryAuthorizationCodeServices inMemoryAuthorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /* 配置token获取合验证时的策略 */
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //配置Jwt内容增强器
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        // List<TokenEnhancer> delegates = new ArrayList<>();
        // delegates.add(jwtTokenEnhancer);
        // delegates.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                // 配置使用redis存储token 令牌
                // 配置存储令牌策略
                .tokenStore(jwtTokenStore)
                //accessToken要和JwtToken进行相互转换
                // .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain);
    }

    // @Bean
    // public TokenStore tokenStore(){
    //     return new JwtTokenStore(accessTokenConverter());
    // }

    // @Bean
    // public JwtAccessTokenConverter accessTokenConverter() {
    //     final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    //
    //     /* SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //     String jwtSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    //     redisUtil.set("jwtSecretKey", jwtSecretKey);*/
    //
    //     converter.setSigningKey(jwtTokenPassword);
    //     converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
    //
    //     return converter;
    // }

}
