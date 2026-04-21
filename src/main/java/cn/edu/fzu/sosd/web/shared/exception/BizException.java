package cn.edu.fzu.sosd.web.shared.exception;

import cn.edu.fzu.sosd.web.shared.error.ResultCode;

/**
 * class name: BizException
 *
 * @author: dkb
 * @description: 业务异常基类
 * @date: 2026/4/18 17:49
 */
public class BizException extends RuntimeException {

    private final int code;
    private final String message;

    public BizException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
