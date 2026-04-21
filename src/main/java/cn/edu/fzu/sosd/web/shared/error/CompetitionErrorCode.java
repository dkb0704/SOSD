package cn.edu.fzu.sosd.web.shared.error;

/**
 * class name: CompetitionErrorCode
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 17:50
 */
public enum CompetitionErrorCode implements ResultCode {

    TEAM_MEMBER_CONFLICT(40001, "队伍成员已加入当前竞赛下的其他队伍"),
    LEADER_CANNOT_BE_MEMBER(40002, "队长不能同时出现在队员列表中"),
    INVALID_TEAM_MEMBER(40003, "队员信息不合法"),
    TEAM_NOT_FOUND(40004, "未报名该赛事");

    private final int code;
    private final String message;

    CompetitionErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
