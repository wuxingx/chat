package com.wuxing.chat.service;

import com.wuxing.chat.pojo.ChatRoom;
import com.wuxing.chat.pojo.PageBean;
import com.wuxing.chat.pojo.RoomRecord;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by wuxing on 2017-1-25.
 */
public interface ChatRoomService {
    List<RoomRecord> showMsg(String cid) throws SQLException, ParseException, IllegalAccessException, InvocationTargetException;

    List<ChatRoom> showList() throws SQLException;

    ChatRoom checkPassword(String id, String password) throws SQLException;

    ChatRoom findByCid(String cid) throws SQLException;

    void sendMsg(RoomRecord roomRecord) throws SQLException;

    PageBean roomHist(String cid, String currpage) throws SQLException, InvocationTargetException, IllegalAccessException;
}
