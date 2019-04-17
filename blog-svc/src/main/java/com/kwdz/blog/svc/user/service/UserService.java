package com.kwdz.blog.svc.user.service;

import com.kwdz.blog.api.user.vo.UserVo;
import com.kwdz.blog.svc.common.service.CommonService;
import com.kwdz.blog.svc.user.entity.UserEntity;

/**
 * @version 0.0.1
 * @author: huyt
 * @date: 2019/4/15 0:50
 */
public interface UserService extends CommonService<UserVo, UserEntity> {

    UserEntity findByUserName(String userName);

}
