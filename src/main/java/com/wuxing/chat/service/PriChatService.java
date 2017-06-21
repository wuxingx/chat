package com.wuxing.chat.service;

import com.wuxing.chat.javaBean.PageBean;
import com.wuxing.chat.javaBean.Sm;
import com.wuxing.chat.javaBean.Smlog;
import com.wuxing.chat.javaBean.UserInfo;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuxing on 2017-2-1.
 */
public interface PriChatService {

    Sm findPC(UserInfo realUser, String uid) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Smlog> findMsg(String id) throws IllegalAccessException, SQLException, InvocationTargetException;

    void changeState(Sm sm) throws SQLException;

    void sendMsg(Smlog smlog) throws SQLException;

    void setState(Sm sm) throws SQLException;

    PageBean showHist(String smid, String currPage) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<Sm> checkState(UserInfo realUser) throws SQLException;

    Sm PCBySmid(String smid) throws IllegalAccessException, SQLException, InvocationTargetException;
}
