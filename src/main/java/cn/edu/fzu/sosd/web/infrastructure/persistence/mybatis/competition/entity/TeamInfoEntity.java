package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.entity;

/**
 * class name: TeamInfoEntity
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 15:37
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 队伍信息表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("TeamInfo")
public class TeamInfoEntity {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 任务ID */
    @TableField("TaskId")
    private Integer taskId;

    /** 团队名称 */
    @TableField("Name")
    private String name;

    /** 作品名称 */
    @TableField("ProjectName")
    private String projectName;

    /** 团队负责人 */
    @TableField("LeaderName")
    private String leaderName;

    /** 团队成员 */
    @TableField("TeamMembers")
    private String teamMembers;

    /** 负责人 */
    @TableField("LeaderId")
    private String leaderId;

    /** 选题 */
    @TableField("Topic")
    private String topic;

    /** 指导老师 */
    @TableField("Advisor")
    private String advisor;

    /** 联系邮箱 */
    @TableField("Email")
    private String email;

    /** 比赛结果 */
    @TableField("RewardResult")
    private String rewardResult;

    /** 文档url */
    @TableField("DocUrl")
    private String docUrl;

    /** 创建时间 */
    @TableField("CreatedAt")
    private Date createdAt;

    /** 更新时间 */
    @TableField("UpdatedAt")
    private Date updatedAt;
}
