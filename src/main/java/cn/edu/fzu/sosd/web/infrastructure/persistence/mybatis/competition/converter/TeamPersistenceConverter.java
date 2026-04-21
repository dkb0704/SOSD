package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.converter;

import cn.edu.fzu.sosd.web.domain.competition.model.Team;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.entity.TeamInfoEntity;

/**
 * class name: TeamPersistenceConverter
 *
 * @author: dkb
 * @description:队伍持久化转换器，负责在领域对象和持久化对象之间进行转换。
 * @date: 2026/4/18 18:05
 */
public class TeamPersistenceConverter {

    private TeamPersistenceConverter() {
    }

    /**
     * 将领域层队伍对象转换为持久化对象。
     */
    public static TeamInfoEntity toEntity(Team team) {
        if (team == null) {
            return null;
        }

        return TeamInfoEntity.builder()
                .id(team.getId())
                .taskId(team.getCompetitionId())
                .name(team.getName())
                .projectName(team.getProjectName())
                .leaderId(team.getLeaderId())
                .leaderName(team.getLeaderName())
                .teamMembers(team.getTeamMembers())
                .topic(team.getTopic())
                .advisor(team.getAdvisor())
                .email(team.getEmail())
                .docUrl(team.getDocUrl())
                .build();
    }

    /**
     * 将持久化对象转换为领域层队伍对象。
     */
    public static Team toDomain(TeamInfoEntity entity) {
        if (entity == null) {
            return null;
        }

        return Team.builder()
                .id(entity.getId())
                .competitionId(entity.getTaskId())
                .name(entity.getName())
                .projectName(entity.getProjectName())
                .leaderId(entity.getLeaderId())
                .leaderName(entity.getLeaderName())
                .teamMembers(entity.getTeamMembers())
                .topic(entity.getTopic())
                .advisor(entity.getAdvisor())
                .email(entity.getEmail())
                .docUrl(entity.getDocUrl())
                .build();
    }
}
