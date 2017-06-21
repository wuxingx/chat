package com.wuxing.chat.service;

import com.wuxing.chat.javaBean.PageBean;
import com.wuxing.chat.javaBean.Sm;
import com.wuxing.chat.javaBean.UserInfo;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by wuxing on 2017-2-1.
 */
public interface UserInfoService {
    PageBean<Sm> findSmList(UserInfo realUser, String currPage) throws IllegalAccessException, SQLException, InvocationTargetException;

    void editUserInfo(UserInfo realUser) throws SQLException;

    void changePW(UserInfo realUser) throws SQLException;
}
