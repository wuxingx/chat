/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.pojo.VipLevel.java
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

public class VipLevel {
    private Integer id;

    private Integer level;

    private Integer shutup;

    private Integer ban;

    private Integer createRoom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getShutup() {
        return shutup;
    }

    public void setShutup(Integer shutup) {
        this.shutup = shutup;
    }

    public Integer getBan() {
        return ban;
    }

    public void setBan(Integer ban) {
        this.ban = ban;
    }

    public Integer getCreateRoom() {
        return createRoom;
    }

    public void setCreateRoom(Integer createRoom) {
        this.createRoom = createRoom;
    }
}