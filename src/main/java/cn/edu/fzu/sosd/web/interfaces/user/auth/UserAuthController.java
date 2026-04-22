package cn.edu.fzu.sosd.web.interfaces.user.auth;

import cn.edu.fzu.sosd.web.application.user.UserAuthApplicationService;
import cn.edu.fzu.sosd.web.interfaces.user.auth.request.LoginRequest;
import cn.edu.fzu.sosd.web.interfaces.user.auth.response.LoginResponse;
import cn.edu.fzu.sosd.web.shared.error.SuccessCode;
import cn.edu.fzu.sosd.web.shared.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class name: UserAuthController
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/22 12:57
 */
@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private UserAuthApplicationService userAuthApplicationService;

    @PostMapping("/login")
    public R<LoginResponse> login(@RequestBody LoginRequest request) {
        //todo 开发阶段统一走这个登陆逻辑 之后改为管理端登陆
        // 管理端登录（仅需学号）
        LoginResponse response = userAuthApplicationService.login(request);
        return R.ok(SuccessCode.LOGIN_SUCCESS,response);
    }

}
