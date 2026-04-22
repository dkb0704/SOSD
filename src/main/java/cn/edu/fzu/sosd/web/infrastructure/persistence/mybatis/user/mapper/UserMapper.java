package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.mapper;

import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * class name: TeamInfoMapper
 *
 * @author: dkb
 * @description: 用户表 Mapper
 * @date: 2026/4/22 15:00
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 按用户主键ID查询用户。
     *
     * @param id 用户主键ID
     * @return 查询到的用户持久化对象
     */
    UserEntity selectById(String id);

    /**
     * 按用户主键ID和密码查询用户。
     *
     * @param id 用户主键ID
     * @param password 登录密码
     * @return 查询到的用户持久化对象
     */
    UserEntity selectByIdAndPassword(@Param("id") String id, @Param("password") String password);

    /**
     * 按学号和密码查询用户。
     *
     * @param studentId 学号
     * @param password 登录密码
     * @return 查询到的用户持久化对象
     */
    UserEntity selectByStudentIdAndPassword(@Param("studentId") String studentId,
                                            @Param("password") String password);
}