package cn.edu.fzu.sosd.web.interfaces.user.auth.response;

import cn.edu.fzu.sosd.web.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class name: LoginResponse
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/22 12:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    /**
     * 当前登录用户信息。
     */
    private LoginUserInfoResponse user;

    /**
     * 访问令牌。
     */
    private String token;
}

