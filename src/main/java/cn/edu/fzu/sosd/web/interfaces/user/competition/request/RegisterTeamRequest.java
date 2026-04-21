package cn.edu.fzu.sosd.web.interfaces.user.competition.request;

import lombok.Data;

/**
 * class name: RegisterTeamRequest
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 13:02
 */
@Data
public class RegisterTeamRequest {
    private String name;
    private String projectName;
    private String teamMembers;
    private String topic;
    private String advisor;
    private String email;
    private String docUrl;
}
