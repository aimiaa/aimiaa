
package org.ami.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "YWltaWFhYWltaWFhYWltaWFhYWltaWFh".getBytes(StandardCharsets.UTF_8)
    );

    /**
     * 生成JWT令牌
     *
     * @param claims 载荷中的自定义数据
     * @return JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 12 * 60 * 60 * 1000))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌字符串
     * @return 载荷中的数据
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
