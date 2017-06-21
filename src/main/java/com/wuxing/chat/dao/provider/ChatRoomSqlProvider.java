/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.provider.ChatRoomSqlProvider.java
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

import com.wuxing.chat.pojo.ChatRoom;

public class ChatRoomSqlProvider {

    public String insertSelective(ChatRoom record) {
        BEGIN();
        INSERT_INTO("tb_chat_room");
        
        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserInfoId() != null) {
            VALUES("user_info_id", "#{userInfoId,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(ChatRoom record) {
        BEGIN();
        UPDATE("tb_chat_room");
        
        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserInfoId() != null) {
            SET("user_info_id = #{userInfoId,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            SET("state = #{state,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}