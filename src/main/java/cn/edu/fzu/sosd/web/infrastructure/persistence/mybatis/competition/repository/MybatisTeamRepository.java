package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.repository;

import cn.edu.fzu.sosd.web.domain.competition.model.Team;
import cn.edu.fzu.sosd.web.domain.competition.repository.TeamRepository;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.converter.TeamPersistenceConverter;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.entity.TeamInfoEntity;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.mapper.TeamInfoMapper;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class name: MybatisTeamRepository
 *
 * @author: dkb
 * @description: 基于 MyBatis 的队伍仓储实现。
 * @date: 2026/4/18 15:35
 */
@Repository
public class MybatisTeamRepository implements TeamRepository {

    @Autowired
    private TeamInfoMapper teamInfoMapper;

    /**
     * 判断给定用户集合中，是否有人已经出现在当前竞赛的其他队伍中。
     * 检查范围包括队长 LeaderId 和队员 TeamMembers。
     */
    @Override
    public boolean existsAnyUserInCompetition(Integer competitionId, Set<String> userIds) {
        if (competitionId == null || userIds == null || userIds.isEmpty()) {
            return false;
        }

        List<TeamInfoEntity> teams = teamInfoMapper.selectByCompetitionId(competitionId);
        if (teams == null || teams.isEmpty()) {
            return false;
        }

        for (TeamInfoEntity team : teams) {
            // 队长重复参加
            if (userIds.contains(team.getLeaderId())) {
                return true;
            }

            // 组员有人重复参加
            Set<String> memberIds = parseMemberIds(team.getTeamMembers());
            for (String memberId : memberIds) {
                if (userIds.contains(memberId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 保存队伍
     */
    @Override
    public Team save(Team team) {
        TeamInfoEntity entity = TeamPersistenceConverter.toEntity(team);
        teamInfoMapper.insert(entity);
        team.setId(entity.getId());
        return team;
    }

    /**
     * 查找在该竞赛中 我的队伍
     * @param competitionId 当前竞赛ID
     * @param userId 当前用户ID
     * @return
     */
    @Override
    public Team findMyTeam(Integer competitionId, String userId) {
        if (competitionId == null || userId == null || userId.isBlank()) {
            return null;
        }

        List<TeamInfoEntity> teams = teamInfoMapper.selectByCompetitionId(competitionId);
        if (teams == null || teams.isEmpty()) {
            return null;
        }

        for (TeamInfoEntity team : teams) {
            // 当前用户是队长，直接返回该队伍
            if (userId.equals(team.getLeaderId())) {
                return TeamPersistenceConverter.toDomain(team);
            }

            // 当前用户是队员，也返回该队伍
            Set<String> memberIds = parseMemberIds(team.getTeamMembers());
            if (memberIds.contains(userId)) {
                return TeamPersistenceConverter.toDomain(team);
            }
        }

        // 当前竞赛下没有找到该用户所属队伍
        return null;
    }

    //todo 目前只有两个类需要使用这个方法，暂不抽离成工具类
    private Set<String> parseMemberIds(String teamMembersJson) {
        if (teamMembersJson == null || teamMembersJson.isBlank()) {
            return Collections.emptySet();
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
