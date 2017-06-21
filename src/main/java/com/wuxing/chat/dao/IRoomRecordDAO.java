/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.IRoomRecordDAO.java
*    @package_name   ： com.wuxing.chat.dao
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
package com.wuxing.chat.dao;

import com.wuxing.chat.dao.provider.RoomRecordSqlProvider;
import com.wuxing.chat.pojo.RoomRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface IRoomRecordDAO {
    @Delete({
        "delete from tb_room_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_room_record (user_info_id, chat_room_id, ",
        "create_time, msg)",
        "values (#{userInfoId,jdbcType=INTEGER}, #{chatRoomId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{msg,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(RoomRecord record);

    @InsertProvider(type=RoomRecordSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(RoomRecord record);

    @Select({
        "select",
        "id, user_info_id, chat_room_id, create_time, msg",
        "from tb_room_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    RoomRecord selectByPrimaryKey(Integer id);

    @UpdateProvider(type=RoomRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoomRecord record);

    @Update({
        "update tb_room_record",
        "set user_info_id = #{userInfoId,jdbcType=INTEGER},",
          "chat_room_id = #{chatRoomId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "msg = #{msg,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoomRecord record);
}