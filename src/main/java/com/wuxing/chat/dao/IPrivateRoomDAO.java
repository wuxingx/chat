/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.IPrivateRoomDAO.java
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

import com.wuxing.chat.dao.provider.PrivateRoomSqlProvider;
import com.wuxing.chat.pojo.PrivateRoom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface IPrivateRoomDAO {
    @Delete({
        "delete from tb_private_room",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_private_room (s_user_info_id, r_user_info_id)",
        "values (#{sUserInfoId,jdbcType=INTEGER}, #{rUserInfoId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PrivateRoom record);

    @InsertProvider(type=PrivateRoomSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(PrivateRoom record);

    @Select({
        "select",
        "id, s_user_info_id, r_user_info_id",
        "from tb_private_room",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    PrivateRoom selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PrivateRoomSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PrivateRoom record);

    @Update({
        "update tb_private_room",
        "set s_user_info_id = #{sUserInfoId,jdbcType=INTEGER},",
          "r_user_info_id = #{rUserInfoId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PrivateRoom record);
}