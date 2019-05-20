package com.kwdz.blog.web.controller.user;

import com.kwdz.blog.api.common.controller.BaseController;
import com.kwdz.blog.api.user.feign.UserFeign;
import com.kwdz.blog.api.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.0.1
 * @author YT.Hu
 * @date 2019/4/17 0:56
 */
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController<UserVo> {

    @Autowired
    private UserFeign userFeign;


}
