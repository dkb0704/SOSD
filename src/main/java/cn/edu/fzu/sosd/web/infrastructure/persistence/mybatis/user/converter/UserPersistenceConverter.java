package cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.converter;

import cn.edu.fzu.sosd.web.domain.user.model.User;
import cn.edu.fzu.sosd.web.infrastructure.persistence.mybatis.user.entity.UserEntity;

/**
 * class name: UserPersistenceConverter
 *
 * @author: dkb
 * @description: 用户持久化转换器，负责在持久化对象和领域对象之间进行转换
 * @date: 2026/4/22 15:05
 */
public class UserPersistenceConverter {

    private UserPersistenceConverter() {
    }

    /**
     * 将持久化对象转换为领域对象。
     *
     * @param entity 用户持久化对象
     * @return 用户领域对象
     */
    public static User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .avatar(entity.getAvatar())
                .gender(entity.getGender())
                .qq(entity.getQq())
                .mobile(entity.getMobile())
                .major(entity.getMajor())
                .studentId(entity.getStudentId())
                .openid(entity.getOpenid())
                .lastModifyTime(entity.getLastModifyTime())
                //todo 未知字段
                .avatarBase64(null)
                .build();
    }


}
