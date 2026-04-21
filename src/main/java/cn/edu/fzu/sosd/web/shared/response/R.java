package cn.edu.fzu.sosd.web.shared.response;

/**
 * class name: R
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 13:32
 */

import cn.edu.fzu.sosd.web.shared.error.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应信息主体
 */
@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    //操作码
    private int code;

    //消息
    private String msg;

    //返回的数据
    private T data;

    //不带数据的成功返回
    public static <T> R<T> ok(ResultCode code) {
        return build(code.getCode(), code.getMessage(), null);
    }

    //带数据的成功返回
    public static <T> R<T> ok(ResultCode code, T data) {
        return build(code.getCode(), code.getMessage(), data);
    }

    public static <T> R<T> fail(int code, String msg) {
        return build(code, msg, null);
    }

    private static <T> R<T> build(int code, String msg, T data) {
        R<T> response = new R<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }
}
