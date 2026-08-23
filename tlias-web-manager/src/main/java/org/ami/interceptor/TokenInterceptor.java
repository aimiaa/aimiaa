package org.ami.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.ami.utils.CurrentHolder;
import org.ami.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        if (requestURI.contains("/login")){
//            log.info("登录请求,放行");
//            return true;
//        }
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()){
            log.info("令牌为空,响应401");
            response.setStatus(401);
            return false;
        }
        try {
            Claims claims = JwtUtils.parseJwt(token);
            CurrentHolder.setCurrentId(claims.get("id", Integer.class));
            log.info("当前用户ID: {}", claims.get("id", Integer.class));
            return true;
        } catch (Exception e) {
            log.info("令牌无效,响应401");
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CurrentHolder.removeCurrentId();
    }
}
