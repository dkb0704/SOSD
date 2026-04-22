package cn.edu.fzu.sosd.web.interfaces.user.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class name: LoginUserInfoResponse
 *
 * @author: dkb
 * @description: 登录成功后返回给前端的用户基础信息
 * @date: 2026/4/22 14:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfoResponse {

    /**
     * 用户主键ID。
     */
    private String id;

    /**
     * 用户昵称。
     */
    private String name;

    /**
     * 用户邮箱。
     */
    private String email;

    /**
     * 用户头像地址。
     */
    private String avatar;

    /**
     * 用户学号。
     */
    private String studentId;
}