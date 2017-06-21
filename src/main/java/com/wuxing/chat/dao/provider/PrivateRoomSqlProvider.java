/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.provider.PrivateRoomSqlProvider.java
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

import com.wuxing.chat.pojo.PrivateRoom;

public class PrivateRoomSqlProvider {

    public String insertSelective(PrivateRoom record) {
        BEGIN();
        INSERT_INTO("tb_private_room");
        
        if (record.getsUserInfoId() != null) {
            VALUES("s_user_info_id", "#{sUserInfoId,jdbcType=INTEGER}");
        }
        
        if (record.getrUserInfoId() != null) {
            VALUES("r_user_info_id", "#{rUserInfoId,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(PrivateRoom record) {
        BEGIN();
        UPDATE("tb_private_room");
        
        if (record.getsUserInfoId() != null) {
            SET("s_user_info_id = #{sUserInfoId,jdbcType=INTEGER}");
        }
        
        if (record.getrUserInfoId() != null) {
            SET("r_user_info_id = #{rUserInfoId,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}