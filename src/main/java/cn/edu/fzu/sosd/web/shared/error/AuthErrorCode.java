package cn.edu.fzu.sosd.web.shared.error;

/**
 * class name: AuthErrorCode
 *
 * @author: dkb
 * @description: 认证模块错误码
 * @date: 2026/4/22 14:22
 */
public enum AuthErrorCode implements ResultCode{

    LOGIN_REQUEST_EMPTY(40101, "登录请求不能为空"),
    LOGIN_PARAM_INVALID(40102, "登录参数不完整"),
    LOGIN_FAILED(40103, "账号或密码错误"),
    UNAUTHORIZED(40104, "未登录"),
    TOKEN_INVALID(40105, "token 无效，请重新登录"),
    USER_NOT_FOUND(40106, "用户不存在");


    private final int code;
    private final String message;

    AuthErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
