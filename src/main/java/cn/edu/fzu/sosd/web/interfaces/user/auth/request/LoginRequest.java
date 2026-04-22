package cn.edu.fzu.sosd.web.interfaces.user.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class name: LoginRequest
 *
 * @author: dkb
 * @description: 登录请求对象
 * @date: 2026/4/22 12:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    /**
     * 用户主键ID。
     */
    private String id;

    /**
     * 用户邮箱。
     * todo 疑似多余设计
     */
    private String email;

    /**
     * 验证码。
     * todo 疑似多余设计
     */
    private String validCode;

    /**
     * 学号。
     */
    private String studentId;

    /**
     * 登录密码。
     * 普通账号密码登录时使用。
     */
    private String password;

}