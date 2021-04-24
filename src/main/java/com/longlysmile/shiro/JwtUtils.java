package com.longlysmile.shiro;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TODO jwt工具类
 *
 * @author wujie
 * @version 1.0
 * @date 2020/10/25 22:36
 */
@Component
@ConfigurationProperties(prefix = "longlysmile.jwt")
@Data
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        Date now = new Date();
        // 过期时间
        Date expireDate = new Date(now.getTime() + expire * 1000);
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(userId + "")
                .setIssuedAt(now).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 获取jwt的信息
     *
     * @param token
     * @return
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
