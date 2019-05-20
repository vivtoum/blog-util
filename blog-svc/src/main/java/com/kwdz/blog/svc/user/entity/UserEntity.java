package com.kwdz.blog.svc.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author huyt
 * @version 0.0.1
 * @date 2019/4/15 1:36
 */
@Entity
@Data
@Table(name = "user", schema = "springbootdemo", catalog = "")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -3393406059107146907L;

    @Id
    @Column(name = "user_id", nullable = false, length = 255)
    private String userId;

    @Column(name = "room_id", nullable = true, length = 255)
    private String roomId;

    @Column(name = "user_idcard", nullable = true, length = 255)
    private String userIdcard;

    @Column(name = "user_name", nullable = true, length = 255)
    private String userName;

    @Column(name = "user_password", nullable = true, length = 255)
    private String userPassword;

    @Column(name = "user_sex", nullable = true, length = 255)
    private String userSex;

    @Column(name = "user_stata", nullable = true)
    private Long userStata;

    @Column(name = "user_tel", nullable = true, length = 255)
    private String userTel;

}
