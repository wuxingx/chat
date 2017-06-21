/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.IDirtyDAO.java
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

import com.wuxing.chat.dao.provider.DirtySqlProvider;
import com.wuxing.chat.pojo.Dirty;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface IDirtyDAO {
    @Delete({
        "delete from tb_dirty",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_dirty (word)",
        "values (#{word,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Dirty record);

    @InsertProvider(type=DirtySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Dirty record);

    @Select({
        "select",
        "id, word",
        "from tb_dirty",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Dirty selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DirtySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Dirty record);

    @Update({
        "update tb_dirty",
        "set word = #{word,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Dirty record);
}