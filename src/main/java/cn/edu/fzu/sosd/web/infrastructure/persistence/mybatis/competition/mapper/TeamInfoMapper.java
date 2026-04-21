package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.mapper;

import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.competition.entity.TeamInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * class name: TeamInfoMapper
 *
 * @author: dkb
 * @description:
 * @date: 2026/4/18 15:40
 */
@Mapper
public interface TeamInfoMapper extends BaseMapper<TeamInfoEntity> {

    //按 competitionId 查出当前竞赛下所有队伍
    List<TeamInfoEntity> selectByCompetitionId(Integer competitionId);
}
