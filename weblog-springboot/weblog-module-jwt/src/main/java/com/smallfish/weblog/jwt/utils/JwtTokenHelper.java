package com.smallfish.weblog.jwt.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: JwtTokenHelper工具类
 **/
@Component
@ConfigurationProperties(prefix = "smallfish.jwt")
public class JwtTokenHelper implements InitializingBean {

    /**
     * 签发人
     */
    @Value("${jwt.issuser}")
    private String issuser;

    /**
     * 密钥
     */
    private Key key;

    /**
     * jwt解析
     */
    private JwtParser jwtParser;

    /**
     * Token 失效时间（分钟）
     */
    @Value("${jwt.tokenExpireTime}")
    private Long tokenExpireTime;

    /**
     * 解码配置文件中配置的 Base64 获取到 key 为密钥
     */
    @Value("${jwt.secret}")
    public void setBase64Key(String base64Key) {
        // 使用Base64解码器给 base64Key 解码为字节数组 再用这个字节数组最为密钥生成HMAC SHA密钥
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Key));
    }

    /**
     * 初始化JwtParser afterPropertiesSet方法Spring框架的一个生命周期回调 在所有属性被注入完成后执行自定义的初始化操作
     * jwtParser 的创建依赖于 issuser 和 key 这些属性，而这些属性通常是在 Spring 容器中通过依赖注入的方式注入的
     * beforePropertiesSet 方法会在所有属性被注入完成后执行，确保 jwtParser 已经被正确初始化
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 指定签发人 密钥
        jwtParser = Jwts.parserBuilder().requireIssuer(issuser)
                // 考虑到不同服务器之间可能存在时钟偏移，setAllowedClockSkewSeconds 用于设置能够容忍的最大的时钟误差
                .setSigningKey(key).setAllowedClockSkewSeconds(10)
                .build();
    }

    /**
     * 生成Token
     */
    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        // 设置Token 失效时间 将当前时间+指定的分钟数计算过期时间
        LocalDateTime expireTime = now.plusMinutes(tokenExpireTime);

        // 根据传入的username生成主体subject
        return Jwts.builder()
                .setSubject(username)
                // 设置签发人
                .setIssuer(issuser)
                // 凭证的颁发时间
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                // 设置过期时间
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
    }

    /**
     * 解析token
     * Jws<Claims> 标识经过验证的JWT
     */
    public Jws<Claims> parseToken(String token) {
        try {
            return jwtParser.parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // 解析过程token不对 报异常
            throw new BadCredentialsException("Token 不可用", e);
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("Token 已过期", e);
        }
    }

    /**
     * 生成一个 Base64 的安全密钥
     */
    private static String generateBase64Key() {
        // 指定哈希算法 生成安全密钥
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // 将密钥进行Base64 编码
        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        return base64Key;
    }

    /**
     * 校验 Token 是否可用
     */
    public void validateToken(String token) {
        jwtParser.parseClaimsJws(token);
    }

    /**
     * 解析 Token 获取用户名
     */
    public String getUsernameByToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String key = generateBase64Key();
        System.out.println("key = " + key);
    }
}
