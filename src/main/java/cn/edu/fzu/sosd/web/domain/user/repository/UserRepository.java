package cn.edu.fzu.sosd.web.domain.user.repository;

import cn.edu.fzu.sosd.web.domain.user.model.User;

/**
 * class name: UserRepository
 *
 * @author: dkb
 * @description: 用户仓储接口
 * @date: 2026/4/22 14:40
 */
public interface UserRepository {

    /**
     * 按用户主键ID查询用户。
     *
     * @param id 用户主键ID
     * @return 查询成功返回用户，否则返回 null
     */
    User findById(String id);

    /**
     * 按用户ID和密码查询用户。
     *
     * @param id 用户主键ID
     * @param password 登录密码
     * @return 登录成功返回用户；否则返回 null
     */
    User findByIdAndPassword(String id, String password);

    /**
     * 按学号和密码查询用户。
     *
     * @param studentId 学号
     * @param password 登录密码
     * @return 登录成功返回用户；否则返回 null
     */
    User findByStudentIdAndPassword(String studentId, String password);
}
