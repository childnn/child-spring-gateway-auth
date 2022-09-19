package org.child.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.child.oauth2.provider.ChildAbstractUserDetailsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/06
 * 认证成功之后调用
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    /**
     * @see org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
     * @see org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ProtectedResourceDetailsConfiguration#oauth2RemoteResource()
     */
    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        // 允许跨域
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        String pwd = ChildAbstractUserDetailsAuthenticationProvider.USERS.get(username);

        Assert.hasText(pwd, "用户名-密码错误。。。");

        // ResponseMessage<DTO_GY_YongHuXx> yongHuItemResponseMessage = yongHuGlApi.getByYongHuId(username);
        //
        // if (Boolean.TRUE.equals(yongHuItemResponseMessage.runIsFailure())) {
        //     throw new UsernameNotFoundException(username);
        // }

        // DTO_GY_YongHuXx yongHuXx = yongHuItemResponseMessage.getReturnData();

        String clientSecret = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();

        clientSecret = "Basic " + Base64.getEncoder().encodeToString(clientSecret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", clientSecret);

        // Map<String, String> headerMap = CoreHeaderInterceptor.headerMap.get();
        // headerMap.forEach(httpHeaders::set);

        // 授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("grant_type", Collections.singletonList(oAuth2ProtectedResourceDetails.getGrantType()));
        map.put("username", Collections.singletonList(username));
        map.put("password", Collections.singletonList(pwd));

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        // 获取 Token
        ResponseEntity<OAuth2AccessToken> exchange = restTemplate.exchange(
                oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST, httpEntity,
                OAuth2AccessToken.class);

        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(exchange));

        // if (exchange.getStatusCode().equals(HttpStatus.OK) && exchange.getBody() != null) {
        //     OAuth2AccessToken oAuth2AccessToken = exchange.getBody();
        //
        //     String token = new ObjectMapper().writeValueAsString(oAuth2AccessToken);
        //
        //     httpServletResponse.getWriter().write(token);
        //
        //     // DTO_AccessToken dtoAccessToken = new DTO_AccessToken();
        //     //
        //     // dtoAccessToken.setAccessToken("Bearer " + oAuth2AccessToken.getValue());
        //     // dtoAccessToken.setAccessTokenValidDeadline(oAuth2AccessToken.getExpiration());
        //     // dtoAccessToken.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
        //     //
        //     // httpServletResponse.getWriter().write(JSON.toJSONString(ResponseMessage.success(dtoAccessToken)));
        // }
    }

}
