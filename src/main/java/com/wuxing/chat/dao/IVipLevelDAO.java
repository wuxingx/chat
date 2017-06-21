/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.IVipLevelDAO.java
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

import com.wuxing.chat.dao.provider.VipLevelSqlProvider;
import com.wuxing.chat.pojo.VipLevel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface IVipLevelDAO {
    @Delete({
        "delete from tb_vip_level",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_vip_level (level, shutup, ",
        "ban, create_room)",
        "values (#{level,jdbcType=INTEGER}, #{shutup,jdbcType=INTEGER}, ",
        "#{ban,jdbcType=INTEGER}, #{createRoom,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(VipLevel record);

    @InsertProvider(type=VipLevelSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(VipLevel record);

    @Select({
        "select",
        "id, level, shutup, ban, create_room",
        "from tb_vip_level",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    VipLevel selectByPrimaryKey(Integer id);

    @UpdateProvider(type=VipLevelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VipLevel record);

    @Update({
        "update tb_vip_level",
        "set level = #{level,jdbcType=INTEGER},",
          "shutup = #{shutup,jdbcType=INTEGER},",
          "ban = #{ban,jdbcType=INTEGER},",
          "create_room = #{createRoom,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(VipLevel record);
}