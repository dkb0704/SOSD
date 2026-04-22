package cn.edu.fzu.sosd.web.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * class name: User
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/22 13:13
 */
//todo 为了兼容旧代码，暂时使用这种形式  直接全量返回用户信息的方式不太好
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户主键ID。
     */
    private String id;

    /**
     * 用户邮箱。
     */
    private String email;

    /**
     * 用户昵称。
     */
    private String name;

    //不太确定这个字段是什么 可能是头像信息
    private String avatar;

    /**
     * 用户性别。
     */
    private String gender;

    /**
     * 用户QQ号。
     */
    private String qq;

    /**
     * 用户手机号。
     */
    private String mobile;

    /**
     * 用户专业。
     */
    private String major;

    /**
     * 用户学号。
     */
    private String studentId;
    //疑似原本想兼容微信登录 被荒废设计
    private String openid;
    /**
     * 最后修改时间。
     */
    private Date lastModifyTime;

    // optional:
//    private List<RoleDo> roles;
//    private List<PermissionDo> permissions;

    //不太确定这个字段是什么
    private String avatarBase64;
}
