/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.provider.VipLevelSqlProvider.java
*    @package_name   ： com.wuxing.chat.dao.provider
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
package com.wuxing.chat.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.wuxing.chat.pojo.VipLevel;

public class VipLevelSqlProvider {

    public String insertSelective(VipLevel record) {
        BEGIN();
        INSERT_INTO("tb_vip_level");
        
        if (record.getLevel() != null) {
            VALUES("level", "#{level,jdbcType=INTEGER}");
        }
        
        if (record.getShutup() != null) {
            VALUES("shutup", "#{shutup,jdbcType=INTEGER}");
        }
        
        if (record.getBan() != null) {
            VALUES("ban", "#{ban,jdbcType=INTEGER}");
        }
        
        if (record.getCreateRoom() != null) {
            VALUES("create_room", "#{createRoom,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(VipLevel record) {
        BEGIN();
        UPDATE("tb_vip_level");
        
        if (record.getLevel() != null) {
            SET("level = #{level,jdbcType=INTEGER}");
        }
        
        if (record.getShutup() != null) {
            SET("shutup = #{shutup,jdbcType=INTEGER}");
        }
        
        if (record.getBan() != null) {
            SET("ban = #{ban,jdbcType=INTEGER}");
        }
        
        if (record.getCreateRoom() != null) {
            SET("create_room = #{createRoom,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}