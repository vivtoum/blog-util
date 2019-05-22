package io.github.vivtoum.blog.api.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YT.Hu
 * @version 0.0.1
 * @since 2019/4/15 23:31
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String roomId;
    private String userIdcard;
    private String userName;
    private String userPassword;
    private String userSex;
    private Long userStata;
    private String userTel;

}
