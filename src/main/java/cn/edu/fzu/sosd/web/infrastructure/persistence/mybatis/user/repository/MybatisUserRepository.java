package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.repository;

import cn.edu.fzu.sosd.web.domain.user.model.User;
import cn.edu.fzu.sosd.web.domain.user.repository.UserRepository;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.converter.UserPersistenceConverter;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.entity.UserEntity;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * class name: MybatisUserRepository
 *
 * @author: dkb
 * @description: 用户仓储实现
 * @date: 2026/4/22 15:08
 */
@Repository
public class MybatisUserRepository implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(String id) {
        UserEntity entity = userMapper.selectById(id);
        return UserPersistenceConverter.toDomain(entity);
    }

    /**
     * 按用户主键ID和密码查询用户。
     *
     * @param id 用户主键ID
     * @param password 登录密码
     * @return 查询成功返回用户，否则返回 null
     */
    @Override
    public User findByIdAndPassword(String id, String password) {
        UserEntity entity = userMapper.selectByIdAndPassword(id, password);
        return UserPersistenceConverter.toDomain(entity);
    }

    /**
     * 按学号和密码查询用户。
     *
     * @param studentId 学号
     * @param password 登录密码
     * @return 查询成功返回用户，否则返回 null
     */
    @Override
    public User findByStudentIdAndPassword(String studentId, String password) {
        UserEntity entity = userMapper.selectByStudentIdAndPassword(studentId, password);
        return UserPersistenceConverter.toDomain(entity);
    }
}