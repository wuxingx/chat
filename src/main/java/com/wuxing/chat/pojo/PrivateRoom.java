/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.pojo.PrivateRoom.java
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

public class PrivateRoom {
    private Integer id;

    private Integer sUserInfoId;

    private Integer rUserInfoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getsUserInfoId() {
        return sUserInfoId;
    }

    public void setsUserInfoId(Integer sUserInfoId) {
        this.sUserInfoId = sUserInfoId;
    }

    public Integer getrUserInfoId() {
        return rUserInfoId;
    }

    public void setrUserInfoId(Integer rUserInfoId) {
        this.rUserInfoId = rUserInfoId;
    }
}