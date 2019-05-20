package com.kwdz.blog.svc.user.dao;

import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.user.entity.UserEntity;

/**
 * @author YT.Hu
 */
public interface UserDao extends CommonDao<UserEntity> {
    /**
     * 根据用户名查找用户信息
     *
     * @param userName 用户名
     * @return 用户实体
     */
    UserEntity findByUserName(String userName);
}
