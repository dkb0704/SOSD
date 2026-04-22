package cn.edu.fzu.sosd.web.infrastructure.security;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * class name: JwtTokenService
 *
 * @author: dkb
 * @description: JWT 令牌服务，负责生成、校验和解析登录令牌
 * @date: 2026/4/22 15:22
 */
@Service
public class JwtTokenService {

    /**
     * JWT 签名密钥。
     */
    @Value("${security.jwt.key}")
    private String jwtKey;

    /**
     * JWT 过期时间。
     */
    @Value("${security.jwt.expire-seconds}")
    private Long expireSeconds;

    /**
     * 根据用户ID生成登录令牌。
     *
     * @param userId 用户主键ID
     * @return JWT 令牌
     */
    public String createTokenByUserId(String userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userId);
        return createToken(payload);
    }

    /**
     * 基于 payload 生成 token，并写入过期时间。
     *
     * @param payload 业务载荷
     * @return JWT token
     */
    public String createToken(Map<String, Object> payload) {
        Map<String, Object> finalPayload = new HashMap<>(payload);

        long nowMillis = System.currentTimeMillis();
        long expireMillis = nowMillis + expireSeconds * 1000;

        finalPayload.put(RegisteredPayload.ISSUED_AT, nowMillis / 1000);
        finalPayload.put(RegisteredPayload.EXPIRES_AT, expireMillis / 1000);

        return JWTUtil.createToken(finalPayload, getKeyBytes());
    }

    /**
     * 从 token 中解析用户 id。
     *
     * @param token JWT token
     * @return 用户 id
     */
    public String getUserId(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        Object userId = jwt.getPayload("userId");

        if (userId == null) {
            return null;
        }

        return String.valueOf(userId);
    }



    /**
     * 校验 token 是否合法且未过期。
     *
     * @param token JWT token
     * @return true 表示合法
     */
    public boolean verify(String token) {
        try {
            JWT jwt = JWTUtil.parseToken(token);

            boolean signatureValid = jwt.setKey(getKeyBytes()).verify();
            if (!signatureValid) {
                return false;
            }

            return jwt.validate(0);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 获取 JWT 密钥字节数组。
     */
    private byte[] getKeyBytes() {
        return jwtKey.getBytes(StandardCharsets.UTF_8);
    }
}