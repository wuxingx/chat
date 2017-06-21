/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.pojo.ChatRoom.java
*    @package_name   ： com.wuxing.chat.pojo
*    @department     ：研发部
*    @author         ：leo ma
*    (C) Copyright Chongqing Targzon Technology Co., Ltd. 2016 
*                       All Rights Reserved.
*------------------------------------------------------------------------------
*    VERSION       DATE          BY       CHANGE/COMMENT
*      1.0        2016.06      leo ma         create
*------------------------------------------------------------------------------
* *****************************************************************************
*    注意： 本内容仅限于某某软件公司内部使用，禁止转发
******************************************************************************/
package com.wuxing.chat.pojo;

import java.util.Date;

public class ChatRoom {
    private Integer id;

    private String name;

    private String password;

    private Date createTime;

    private Integer userInfoId;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}