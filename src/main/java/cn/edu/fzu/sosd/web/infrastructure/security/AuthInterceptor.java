package cn.edu.fzu.sosd.web.infrastructure.security;

import cn.edu.fzu.sosd.web.domain.user.model.User;
import cn.edu.fzu.sosd.web.domain.user.repository.UserRepository;
import cn.edu.fzu.sosd.web.shared.error.AuthErrorCode;
import cn.edu.fzu.sosd.web.shared.exception.BizException;
import cn.edu.fzu.sosd.web.shared.security.CurrentUser;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * class name: AuthInterceptor
 *
 * @author: dkb
 * @description: 登录认证拦截器，负责解析请求中的 token 并写入用户上下文
 * @date: 2026/4/22 16:13
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 预检请求直接放行，避免跨域场景下被登录校验拦住。
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 从请求头中获取登录令牌。
        String header = request.getHeader("Authorization");
        if (header == null || header.isBlank()) {
            throw new BizException(AuthErrorCode.UNAUTHORIZED);
        }

        // 兼容前端传 Bearer token 的形式。
        String token = header;
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 先校验 token 是否合法。
        if (!jwtTokenService.verify(token)) {
            throw new BizException(AuthErrorCode.TOKEN_INVALID);
        }

        // 再从 token 中解析当前登录用户ID。
        String userId = jwtTokenService.getUserId(token);
        if (userId == null || userId.isBlank()) {
            throw new BizException(AuthErrorCode.TOKEN_INVALID);
        }

        // 根据用户ID查询当前登录用户，确保 token 对应的用户仍然存在。
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new BizException(AuthErrorCode.USER_NOT_FOUND);
        }

        // 将领域层用户对象转换为当前请求上下文中的登录用户快照。
        CurrentUser currentUser = CurrentUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .avatar(user.getAvatar())
                .gender(user.getGender())
                .qq(user.getQq())
                .mobile(user.getMobile())
                .major(user.getMajor())
                .studentId(user.getStudentId())
                .openid(user.getOpenid())
                .lastModifyTime(user.getLastModifyTime())
                .avatarBase64(user.getAvatarBase64())
                .build();

        // 写入用户上下文，供后续 controller / application 层使用。
        UserInfoContext.setCurrentUser(currentUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) {
        // 请求结束后清理 ThreadLocal，避免线程复用带来脏数据污染。
        UserInfoContext.clear();
    }
}