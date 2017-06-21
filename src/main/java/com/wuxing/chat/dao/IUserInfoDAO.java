/******************************************************************************
*    @project_name   ： 天掌火锅网平台
*    @file_name      ： com.wuxing.chat.dao.IUserInfoDAO.java
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

import com.wuxing.chat.dao.provider.UserInfoSqlProvider;
import com.wuxing.chat.pojo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

public interface IUserInfoDAO {
    @Delete({
        "delete from tb_user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tb_user_info (user_id, nickName, ",
        "email, image, code, ",
        "is_shutup, is_ban)",
        "values (#{userId,jdbcType=INTEGER}, #{nickname,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{image,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, ",
        "#{isShutup,jdbcType=INTEGER}, #{isBan,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserInfo record);

    @InsertProvider(type=UserInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(UserInfo record);

    @Select({
        "select",
        "id, user_id, nickName, email, image, code, is_shutup, is_ban",
        "from tb_user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserInfo record);

    @Update({
        "update tb_user_info",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "nickName = #{nickname,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "image = #{image,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "is_shutup = #{isShutup,jdbcType=INTEGER},",
          "is_ban = #{isBan,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserInfo record);
}