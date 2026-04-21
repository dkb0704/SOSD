package cn.edu.fzu.sosd.web.domain.competition.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class name: Team
 *
 * @author: dkb
 * @description: 竞赛队伍领域对象
 * @date: 2026/4/18 18:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private Integer id;

    /**
     * 所属竞赛ID。
     */
    private Integer competitionId;

    /**
     * 队伍名称。
     */
    private String name;

    /**
     * 项目名称。
     */
    private String projectName;

    /**
     * 队长用户ID。
     */
    private String leaderId;

    /**
     * 队长姓名。
     */
    private String leaderName;

    /**
     * 队员信息，当前阶段沿用旧结构，使用 JSON 字符串存储。
     */
    private String teamMembers;

    /**
     * 选题方向。
     */
    private String topic;

    /**
     * 指导老师。
     */
    private String advisor;

    /**
     * 联系邮箱。
     */
    private String email;

    /**
     * 相关文档地址。
     */
    private String docUrl;
}
