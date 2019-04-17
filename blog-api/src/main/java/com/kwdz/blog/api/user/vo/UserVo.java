package com.kwdz.blog.api.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @version 0.0.1
 * @author: huyt
 * @date: 2019/4/15 23:31
 */
@Data
public class UserVo {

    private String userId;
    private String roomId;
    private String userIdcard;
    private String userName;
    private String userPassword;
    private String userSex;
    private Long userStata;
    private String userTel;

}
