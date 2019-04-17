package com.kwdz.blog.svc.user.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @version 0.0.1
 * @author: huyt
 * @date: 2019/4/15 1:36
 */
@Entity
@Table(name = "user", schema = "springbootdemo", catalog = "")
public class UserEntity {
    private String userId;
    private String roomId;
    private String userIdcard;
    private String userName;
    private String userPassword;
    private String userSex;
    private Long userStata;
    private String userTel;

    @Id
    @Column(name = "user_id", nullable = false, length = 255)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "room_id", nullable = true, length = 255)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "user_idcard", nullable = true, length = 255)
    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password", nullable = true, length = 255)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_sex", nullable = true, length = 255)
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_stata", nullable = true)
    public Long getUserStata() {
        return userStata;
    }

    public void setUserStata(Long userStata) {
        this.userStata = userStata;
    }

    @Basic
    @Column(name = "user_tel", nullable = true, length = 255)
    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(userIdcard, that.userIdcard) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(userSex, that.userSex) &&
                Objects.equals(userStata, that.userStata) &&
                Objects.equals(userTel, that.userTel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roomId, userIdcard, userName, userPassword, userSex, userStata, userTel);
    }
}
