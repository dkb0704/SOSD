package cn.edu.fzu.sosd.web.shared.error;

/**
 * class name: SuccessCode
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 13:18
 */
public enum SuccessCode implements ResultCode {

    SUCCESS(200, "操作成功"),
    TEAM_REGISTER_SUCCESS(20001, "报名成功"),
    TEAM_QUERY_SUCCESS(20002, "查询成功");

    private final int code;
    private final String message;

    SuccessCode(int code, String message) {
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
