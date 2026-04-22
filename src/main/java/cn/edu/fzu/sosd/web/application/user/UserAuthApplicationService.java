package cn.edu.fzu.sosd.web.application.user;

import cn.edu.fzu.sosd.web.domain.user.model.User;
import cn.edu.fzu.sosd.web.domain.user.repository.UserRepository;
import cn.edu.fzu.sosd.web.infrastructure.security.JwtTokenService;
import cn.edu.fzu.sosd.web.interfaces.user.auth.request.LoginRequest;
import cn.edu.fzu.sosd.web.interfaces.user.auth.response.LoginResponse;
import cn.edu.fzu.sosd.web.interfaces.user.auth.response.LoginUserInfoResponse;
import cn.edu.fzu.sosd.web.shared.error.AuthErrorCode;
import cn.edu.fzu.sosd.web.shared.exception.BizException;
import cn.edu.fzu.sosd.web.shared.util.PasswdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class name: UserAuthApplicationService
 *
 * @author: dkb
 * @description: 用户认证应用服务
 * @date: 2026/4/22 13:24
 */
@Service
public class UserAuthApplicationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    /**
     * 用户登录。
     * @param request 登录请求
     * @return 登录响应
     */
    public LoginResponse login(LoginRequest request) {
        // 基础请求校验
        validateLoginRequest(request);

        // 查询登录用户
        User user = findLoginUser(request);
        if (user == null) {
            throw new BizException(AuthErrorCode.LOGIN_FAILED);
        }
        // token 生成
        String token = jwtTokenService.createTokenByUserId(user.getId());

        // 组装返回结果
        LoginUserInfoResponse userInfo = LoginUserInfoResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .studentId(user.getStudentId())
                .build();

        return LoginResponse.builder()
                .user(userInfo)
                .token(token)
                .build();
    }

    /**
     * 校验登录请求是否合法。
     */
    private void validateLoginRequest(LoginRequest request) {
        if (request == null) {
            throw new BizException(AuthErrorCode.LOGIN_REQUEST_EMPTY);
        }

        boolean hasId = request.getId() != null && !request.getId().isBlank();
        boolean hasStudentId = request.getStudentId() != null && !request.getStudentId().isBlank();
        boolean hasPassword = request.getPassword() != null && !request.getPassword().isBlank();

        if ((!hasId && !hasStudentId) || !hasPassword) {
            throw new BizException(AuthErrorCode.LOGIN_PARAM_INVALID);
        }
    }
    /**
     * 按当前请求中的登录标识查询用户。
     * 当前阶段优先支持 id + password，其次兼容 studentId + password。
     * todo 之后支持更多登录方式
     */
    private User findLoginUser(LoginRequest request) {
        // 加密
        String encodedPassword = PasswdUtil.encode(request.getPassword());

        if (request.getId() != null && !request.getId().isBlank()) {
            return userRepository.findByIdAndPassword(request.getId(), encodedPassword);
        }

        return userRepository.findByStudentIdAndPassword(request.getStudentId(), encodedPassword);
    }

}