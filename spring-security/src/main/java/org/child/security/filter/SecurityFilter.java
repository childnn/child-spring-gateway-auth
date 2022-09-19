package org.child.security.filter;

import org.child.security.entity.LoginUser;
import org.child.security.util.CacheUtil;
import org.child.security.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
 * @see org.springframework.security.web.DefaultSecurityFilterChain
 * @see org.springframework.security.web.FilterChainProxy
 * @since 2022/09/09
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    // private static final List<String> PREFIX_BEARER = Arrays.asList("bearer", "Bearer");

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // 获取请求
        String authorization = request.getHeader("Authorization");
        // 没有 token 直接放行
        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 截取 token
        String token = authorization.substring(7);
        String username;
        try {
            username = JwtUtil.parseToken(token).getClaim("username").asString();
        } catch (Exception e) {
            throw new BadCredentialsException("无效token");
        }

        // 缓存
        LoginUser loginUser = CacheUtil.get(username);
        if (loginUser == null) {
            throw new RuntimeException("无效token");
        }

        // 将授权状态设置到 SecurityContext
        // 使用三个参数构造, super.setAuthenticated(true);
        // 表示已经认证通过
        // todo: 授权信息 authorities
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 放行
        filterChain.doFilter(request, response);
    }

}
