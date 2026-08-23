package org.ami.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.ami.utils.JwtUtils;

import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //1.获取请求路径
        String requestURI = req.getRequestURI();

        //2.判断是否是登录请求,登录请求放行
        if (requestURI.contains("/login")){
            chain.doFilter(request, response);
            return;
        }

        //3.从请求头中获取token
        String token = req.getHeader("token");

        //4.如果token有效,放行,无效,返回错误信息
        if (token == null || token.isEmpty()){
            log.info("令牌为空,响应401");
            resp.setStatus(401);
            return;
        }
        try {
            JwtUtils.parseJwt(token);
            log.info("令牌有效,放行");
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.info("令牌无效,响应401");
            resp.setStatus(401);
        }

    }
}
