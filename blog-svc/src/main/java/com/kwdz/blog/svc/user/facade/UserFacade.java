package com.kwdz.blog.svc.user.facade;

import com.kwdz.blog.api.user.vo.UserVo;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import com.kwdz.blog.svc.user.entity.UserEntity;
import com.kwdz.blog.svc.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.0.1
 * @author: huyt
 * @date: 2019/4/15 0:53
 */

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserFacade extends CommonFacade<UserVo, UserEntity> {
    @Autowired
    private UserService userService;


    @GetMapping("getName/{username}")
    UserEntity findByUserName(@PathVariable("username") String userName) {
        log.info(userService.findByUserName(userName).toString());
        return userService.findByUserName(userName);
    }
}
