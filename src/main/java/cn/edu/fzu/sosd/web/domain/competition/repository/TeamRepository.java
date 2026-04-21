package cn.edu.fzu.sosd.web.domain.competition.repository;

import cn.edu.fzu.sosd.web.domain.competition.model.Team;

import java.util.Set;

/**
 * class name: TeamRepository
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 14:59
 */
public interface TeamRepository {

    /**
     * 判断给定用户集合中，是否有人已经出现在当前竞赛的其他队伍中。
     * 检查范围包括队长 LeaderId 和队员 TeamMembers。
     *
     * @param competitionId 当前竞赛ID
     * @param userIds 待检查的用户ID集合
     * @return 只要有人已存在于当前竞赛下的其他队伍中，则返回 true
     */
    boolean existsAnyUserInCompetition(Integer competitionId, Set<String> userIds);

    /**
     * 保存队伍信息，并返回带有主键ID的队伍对象。
     *
     * @param team 待保存的队伍领域对象
     * @return 保存后的队伍对象
     */
    Team save(Team team);

    /**
     * 查询当前用户在指定竞赛下所属的队伍。
     * 当前用户既可能是队长，也可能出现在队员列表中。
     *
     * @param competitionId 当前竞赛ID
     * @param userId 当前用户ID
     * @return 查询到的队伍；如果不存在则返回 null
     */
    Team findMyTeam(Integer competitionId, String userId);
}
