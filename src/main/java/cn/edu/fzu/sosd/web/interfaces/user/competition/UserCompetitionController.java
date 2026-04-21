package cn.edu.fzu.sosd.web.interfaces.user.competition;

import cn.edu.fzu.sosd.web.application.competition.TeamApplicationService;
import cn.edu.fzu.sosd.web.domain.competition.model.Team;
import cn.edu.fzu.sosd.web.infrastructure.security.UserInfoContext;
import cn.edu.fzu.sosd.web.interfaces.user.competition.request.RegisterTeamRequest;
import cn.edu.fzu.sosd.web.shared.error.SuccessCode;
import cn.edu.fzu.sosd.web.shared.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class name: UserCompetitionController
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 12:59
 */
@Slf4j
@RestController
@RequestMapping("/user/competition")
public class UserCompetitionController {

    @Autowired
    private TeamApplicationService teamApplicationService;

    /**
     * 队伍报名某一个竞赛
     * @param competitionId 竞赛的id 锁定某一个竞赛
     * @param registerTeamRequest 用户报名请求信息
     * @return 成功报名信息
     */
    @PostMapping("/register/{competitionId}")
    public R<Void> registerCompetition(@PathVariable Integer competitionId,
                                       @RequestBody RegisterTeamRequest registerTeamRequest) {
        teamApplicationService.registerTeam(competitionId, registerTeamRequest);
        if (UserInfoContext.getCurrentUser() != null) {
            log.info("用户{}报名信息{}", UserInfoContext.getCurrentUser().getId(), registerTeamRequest);
        }
        return R.ok(SuccessCode.TEAM_REGISTER_SUCCESS);
    }

    /**
     *
     * @param competitionId 竞赛的id 锁定某一个竞赛
     * @return 成功操作信息和队伍信息
     */
    @GetMapping("/team/{competitionId}")
    public R<Team> getMyTeam(@PathVariable Integer competitionId) {
        Team team = teamApplicationService.getMyTeam(competitionId);
        if (UserInfoContext.getCurrentUser() != null) {
            log.info("用户{}查询是否报名id={}的竞赛", UserInfoContext.getCurrentUser().getId(), competitionId);
        }
        return R.ok(SuccessCode.TEAM_QUERY_SUCCESS, team);
    }
}
