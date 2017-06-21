package com.wuxing.chat.service.impl;

import com.wuxing.chat.dao.ChatRoomDao;
import com.wuxing.chat.javaBean.ChatRoom;
import com.wuxing.chat.javaBean.PageBean;
import com.wuxing.chat.javaBean.RoomLog;
import com.wuxing.chat.service.ChatRoomService;
import org.springframework.stereotype.Service;
import utils.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by wuxing on 2017-1-25.
 */
@Service
public class ChatRoomServiceImp implements ChatRoomService {
    ChatRoomDao chatRoomDao = (ChatRoomDao) BeanFactory.getBean("ChatRoomDao");

    @Override
    public List<RoomLog> showMsg(String id) throws SQLException, ParseException, IllegalAccessException, InvocationTargetException {
        List<RoomLog> roomLoglist = chatRoomDao.showMsg(id);
        return roomLoglist;
    }

    @Override
    public List<ChatRoom> showList() throws SQLException {
        return chatRoomDao.showList();
    }

    @Override
    public ChatRoom checkPassword(String id, String password) throws SQLException {
        return chatRoomDao.checkPassword(id,password);
    }

    @Override
    public ChatRoom findByCid(String cid) throws SQLException {
        return chatRoomDao.findByCid(cid);
    }

    @Override
    public void sendMsg(RoomLog roomLog) throws SQLException {
        chatRoomDao.sendMsg(roomLog);
    }

    @Override
    public PageBean roomHist(String cid, String currpage) throws SQLException, InvocationTargetException, IllegalAccessException {
        PageBean<RoomLog> roomLogPageBean = new PageBean<>();
        roomLogPageBean.setCurrPage(Integer.parseInt(currpage));
        int pageSize = 10;
        roomLogPageBean.setPageSize(pageSize);
        Integer rMsg = chatRoomDao.findRMsg(cid);
        roomLogPageBean.setTotalCount(rMsg);
        double tcount = rMsg;
        Double ceil = Math.ceil(tcount / pageSize);
        roomLogPageBean.setTotalPage(ceil.intValue());
        int begin = (Integer.parseInt(currpage) - 1) * pageSize;
        List pageByCid = chatRoomDao.findPageByCid(cid, begin, pageSize);
        roomLogPageBean.setList(pageByCid);
        return roomLogPageBean;
    }


}
