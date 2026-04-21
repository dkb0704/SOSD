package cn.edu.fzu.sosd.web.shared.security;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * class name: CurrentUser
 *
 * @author: dkb
 * @description: 当前用户信息
 * @date: 2026/4/18 14:45
 */
//todo 为了兼容旧代码，暂时使用这种形式  直接全量返回用户信息的方式不太好
@Data
public class CurrentUser {
    private String id;
    private String email;
    private String name;
    private String password;

    //不太确定这个字段是什么
    private String avatar;

    private String gender;
    private String qq;
    private String mobile;
    private String major;
    private String studentId;
    private String openid;
    private Date lastModifyTime;

    // optional:
//    private List<RoleDo> roles;
//    private List<PermissionDo> permissions;

    //不太确定这个字段是什么
    private String avatarBase64;
}
