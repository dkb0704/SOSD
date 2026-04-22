package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * class name: UserEntity
 *
 * @author: dkb
 * @description: 用户持久化对象
 * @date: 2026/4/22 14:56
 */
@Data
@TableName("User")
public class UserEntity {

    /**
     * 用户主键ID。
     */
    @TableField("Id")
    private String id;

    /**
     * 用户邮箱。
     */
    @TableField("Email")
    private String email;

    /**
     * 用户昵称。
     */
    @TableField("Name")
    private String name;

    /**
     * 用户密码。
     */
    @TableField("Password")
    private String password;

    /**
     * 用户头像地址。
     */
    @TableField("Avatar")
    private String avatar;

    /**
     * 用户性别。
     */
    @TableField("Gender")
    private String gender;

    /**
     * 用户QQ号。
     */
    @TableField("QQ")
    private String qq;

    /**
     * 用户手机号。
     */
    @TableField("Mobile")
    private String mobile;

    /**
     * 用户专业。
     */
    @TableField("Major")
    private String major;

    /**
     * 用户学号。
     */
    @TableField("StudentId")
    private String studentId;

    /**
     * 用户微信 OpenId。
     */
    @TableField("OpenId")
    private String openid;

    /**
     * 最后修改时间。
     */
    @TableField("LastModifyTime")
    private Date lastModifyTime;
}