package com.kwdz.blog.svc.user.service;

import com.kwdz.blog.api.user.vo.UserVo;
import com.kwdz.blog.svc.common.service.CommonService4RedisImpl;
import com.kwdz.blog.svc.common.service.CommonServiceImpl;
import com.kwdz.blog.svc.user.dao.UserDao;
import com.kwdz.blog.svc.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @version 0.0.1
 * @author huyt
 * @date 2019/4/15 0:51
 */
@Transactional
@Service
public class UserServiceImpl extends CommonService4RedisImpl<UserVo, UserEntity> implements UserService {
    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserEntity findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

}
