/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.provider.PrivateRecordSqlProvider.java
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

import com.wuxing.chat.pojo.PrivateRecord;

public class PrivateRecordSqlProvider {

    public String insertSelective(PrivateRecord record) {
        BEGIN();
        INSERT_INTO("tb_private_record");
        
        if (record.getUserInfoId() != null) {
            VALUES("user_info_id", "#{userInfoId,jdbcType=INTEGER}");
        }
        
        if (record.getPrivateRoomId() != null) {
            VALUES("private_room_id", "#{privateRoomId,jdbcType=INTEGER}");
        }
        
        if (record.getSendTime() != null) {
            VALUES("send_time", "#{sendTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMsg() != null) {
            VALUES("msg", "#{msg,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(PrivateRecord record) {
        BEGIN();
        UPDATE("tb_private_record");
        
        if (record.getUserInfoId() != null) {
            SET("user_info_id = #{userInfoId,jdbcType=INTEGER}");
        }
        
        if (record.getPrivateRoomId() != null) {
            SET("private_room_id = #{privateRoomId,jdbcType=INTEGER}");
        }
        
        if (record.getSendTime() != null) {
            SET("send_time = #{sendTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMsg() != null) {
            SET("msg = #{msg,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}