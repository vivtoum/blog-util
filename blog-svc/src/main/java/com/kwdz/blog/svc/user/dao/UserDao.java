package com.kwdz.blog.svc.user.dao;

import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.user.entity.UserEntity;

public interface UserDao extends CommonDao<UserEntity> {
    UserEntity findByUserName(String userName);
}
