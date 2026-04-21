package cn.edu.fzu.sosd.web.application.competition;

import cn.edu.fzu.sosd.web.domain.competition.model.Team;
import cn.edu.fzu.sosd.web.domain.competition.repository.TeamRepository;
import cn.edu.fzu.sosd.web.domain.competition.service.TeamRegistrationDomainService;
import cn.edu.fzu.sosd.web.infrastructure.security.UserInfoContext;
import cn.edu.fzu.sosd.web.interfaces.user.competition.request.RegisterTeamRequest;
import cn.edu.fzu.sosd.web.shared.error.CompetitionErrorCode;
import cn.edu.fzu.sosd.web.shared.exception.BizException;
import cn.edu.fzu.sosd.web.shared.security.CurrentUser;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * class name: TeamApplicationService
 *
 * @author: dkb
 * @description: 竞赛模块 关于队伍的功能
 * @date: 2026/4/18 14:01
 */
@Service
public class TeamApplicationService {

    @Autowired
    private TeamRegistrationDomainService teamRegistrationDomainService;

    @Autowired
    private TeamRepository teamRepository;

    //队伍报名
    public void registerTeam(Integer competitionId, RegisterTeamRequest request) {
        CurrentUser currentUser = UserInfoContext.getCurrentUser();
        String leaderId = currentUser.getId();
        String leaderName = currentUser.getName();

        Set<String> memberIds = extractMemberIds(request.getTeamMembers());

        //校验
        teamRegistrationDomainService.validateRegister(competitionId, leaderId, memberIds);

        //保存
        Team team = Team.builder()
                .competitionId(competitionId)
                .leaderId(leaderId)
                .leaderName(leaderName)
                .name(request.getName())
                .projectName(request.getProjectName())
                .teamMembers(request.getTeamMembers())
                .topic(request.getTopic())
                .advisor(request.getAdvisor())
                .email(request.getEmail())
                .docUrl(request.getDocUrl())
                .build();
        teamRepository.save(team);
    }

    //获取我的队伍
    public Team getMyTeam(Integer competitionId) {
        CurrentUser currentUser = UserInfoContext.getCurrentUser();
        String userId = currentUser.getId();

        Team team = teamRepository.findMyTeam(competitionId, userId);
        if (team == null) {
            throw new BizException(CompetitionErrorCode.TEAM_NOT_FOUND);
        }
        return team;
    }

    //todo 目前只有两个类使用到这个方法，暂时不抽离成工具类
    /**
     * 从请求中的队员 JSON 字符串里提取全部成员 id。
     */
    private Set<String> extractMemberIds(String teamMembersJson) {
        if (teamMembersJson == null || teamMembersJson.isBlank()) {
            return new HashSet<>();
        }

        JSONArray jsonArray = JSONUtil.parseArray(teamMembersJson);
        Set<String> memberIds = new HashSet<>();
        for (Object item : jsonArray) {
            if (!(item instanceof JSONObject jsonObject)) {
                continue;
            }
            String userId = jsonObject.getStr("userId");
            if (userId != null && !userId.isBlank()) {
                memberIds.add(userId);
            }
        }
        return memberIds;
    }
}
