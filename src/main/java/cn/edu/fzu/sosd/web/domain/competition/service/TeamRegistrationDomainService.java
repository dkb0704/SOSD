package cn.edu.fzu.sosd.web.domain.competition.service;

import cn.edu.fzu.sosd.web.domain.competition.repository.TeamRepository;
import cn.edu.fzu.sosd.web.shared.error.CompetitionErrorCode;
import cn.edu.fzu.sosd.web.shared.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * class name: TeamRegistrationDomainService
 *
 * @author: dkb
 * @description: 队伍注册时的业务规则校验
 * @date: 2026/4/18 14:57
 */
@Service
public class TeamRegistrationDomainService {

    @Autowired
    private TeamRepository teamRepository;

    /**
     * 校验当前队伍是否可以在指定竞赛下完成注册。
     * 校验范围包含队长本人以及请求中的全部队员。
     */
    public void validateRegister(Integer competitionId, String leaderId, Set<String> memberIds) {
        // 基础校验
        validateRequestMembers(leaderId, memberIds);

        Set<String> candidateUserIds = new HashSet<>();
        candidateUserIds.add(leaderId);
        candidateUserIds.addAll(memberIds);

        //检查队长/队员 是否在其他队伍中
        if (teamRepository.existsAnyUserInCompetition(competitionId, candidateUserIds)) {
            throw new BizException(CompetitionErrorCode.TEAM_MEMBER_CONFLICT);
        }
    }

    /**
     * 校验本次请求中的成员集合是否合法。
     */
    private void validateRequestMembers(String leaderId, Set<String> memberIds) {
        if (leaderId == null || leaderId.isBlank()) {
            throw new BizException(CompetitionErrorCode.INVALID_TEAM_MEMBER);
        }
        if (memberIds == null) {
            throw new BizException(CompetitionErrorCode.INVALID_TEAM_MEMBER);
        }
        if (memberIds.stream().anyMatch(id -> id == null || id.isBlank())) {
            throw new BizException(CompetitionErrorCode.INVALID_TEAM_MEMBER);
        }

        // 队长不能同时出现在自己的队员列表中。
        if (memberIds.contains(leaderId)) {
            throw new BizException(CompetitionErrorCode.LEADER_CANNOT_BE_MEMBER);
        }
    }
}
