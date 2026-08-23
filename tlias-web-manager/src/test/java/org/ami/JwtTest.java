package org.ami;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "YWltaWFhYWltaWFhYWltaWFhYWltaWFh".getBytes(StandardCharsets.UTF_8)
    );

    /**
     * 生成Jwt令牌
     */
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("username","admin");
        String jwt = Jwts.builder()
                .claims(dataMap)
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(SECRET_KEY)
                .compact();
        System.out.println(jwt);
    }
    /**
     * 解析Jwt令牌
     */
    @Test
    public void testParseJwt(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzg2MTIwOTc2fQ.kWWf8PJPF6xL-dbeGlLBeCiwRhDq2lRLbT2r4VzcIoE";
        // 1. 创建JWT解析器构建器
        // 2. 设置用于验证签名的密钥（必须与生成令牌时使用的密钥一致）
        // 3. 构建解析器实例
        // 4. 解析JWT字符串，同时验证签名和过期时间
        // 5. 获取载荷部分（即存储的自定义数据，如username等）
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        System.out.println(claims);
    }
}
