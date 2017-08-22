package com.wonders.core.entity;
import com.wonders.core.base.IDEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_sys_user")
public class User extends IDEntity {
    /**
     * 用户名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 用户密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 密码长度
     */
    @Column(name = "PASSWORD_LENGTH")
    private Integer passwordLength;

    /**
     * 用户姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 用户性别1-男2-女
     */
    @Column(name = "SEX")
    private String sex;

    /**
     * 联系电话
     */
    @Column(name = "TEL")
    private String tel;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 用户是否可删除 0:不可删除 1:可删除
     */
    @Column(name = "CAN_DELETE")
    private String canDelete;

    /**
     * 用户状态0-无效1-有效
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 获取用户名
     *
     * @return USERNAME - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取用户密码
     *
     * @return PASSWORD - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码长度
     *
     * @return PASSWORD_LENGTH - 密码长度
     */
    public Integer getPasswordLength() {
        return passwordLength;
    }

    /**
     * 设置密码长度
     *
     * @param passwordLength 密码长度
     */
    public void setPasswordLength(Integer passwordLength) {
        this.passwordLength = passwordLength;
    }

    /**
     * 获取用户姓名
     *
     * @return NAME - 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户姓名
     *
     * @param name 用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户性别1-男2-女
     *
     * @return SEX - 用户性别1-男2-女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置用户性别1-男2-女
     *
     * @param sex 用户性别1-男2-女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取联系电话
     *
     * @return TEL - 联系电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置联系电话
     *
     * @param tel 联系电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取邮箱
     *
     * @return EMAIL - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户是否可删除 0:不可删除 1:可删除
     *
     * @return CAN_DELETE - 用户是否可删除 0:不可删除 1:可删除
     */
    public String getCanDelete() {
        return canDelete;
    }

    /**
     * 设置用户是否可删除 0:不可删除 1:可删除
     *
     * @param canDelete 用户是否可删除 0:不可删除 1:可删除
     */
    public void setCanDelete(String canDelete) {
        this.canDelete = canDelete;
    }

    /**
     * 获取用户状态0-无效1-有效
     *
     * @return STATUS - 用户状态0-无效1-有效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置用户状态0-无效1-有效
     *
     * @param status 用户状态0-无效1-有效
     */
    public void setStatus(String status) {
        this.status = status;
    }

}