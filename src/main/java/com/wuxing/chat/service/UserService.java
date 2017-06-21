package com.wuxing.chat.service;

import com.wuxing.chat.javaBean.Dirty;
import com.wuxing.chat.javaBean.User;
import com.wuxing.chat.javaBean.UserInfo;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuxingx on 2017-1-22.
 */
public interface UserService {
    UserInfo login(User user) throws SQLException, InvocationTargetException, IllegalAccessException;

    void regist(User user, UserInfo userInfo) throws SQLException;

    User checkName(String username) throws SQLException;

    UserInfo checkEmail(String email) throws SQLException;

    UserInfo active(String code) throws SQLException, InvocationTargetException, IllegalAccessException;

    void updateUser(UserInfo activeUser) throws SQLException;

    UserInfo checkEmailUserinfo(String username, String email) throws SQLException;

    void reSendEmail(UserInfo checkUser) throws SQLException;

    List<Dirty> showDirty() throws SQLException;
}
