package com.wuxing.chat.service.impl;

import com.wuxing.chat.dao.PriChatDao;
import com.wuxing.chat.javaBean.PageBean;
import com.wuxing.chat.javaBean.Sm;
import com.wuxing.chat.javaBean.Smlog;
import com.wuxing.chat.javaBean.UserInfo;
import com.wuxing.chat.service.PriChatService;
import org.springframework.stereotype.Service;
import utils.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuxing on 2017-2-1.
 */
@Service
public class PriChatServiceImp implements PriChatService {
    PriChatDao priChatDao = (PriChatDao) BeanFactory.getBean("PriChatDao");

    @Override
    public Sm findPC(UserInfo realUser, String uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        return priChatDao.findPC(realUser,uid);

    }

    @Override
    public List<Smlog> findMsg(String id) throws IllegalAccessException, SQLException, InvocationTargetException {
        return priChatDao.findMsg(id);
    }

    @Override
    public void changeState(Sm sm) throws SQLException {
        priChatDao.changeState(sm);
    }

    @Override
    public void sendMsg(Smlog smlog) throws SQLException {
        priChatDao.sendMsg(smlog);
    }

    @Override
    public void setState(Sm sm) throws SQLException {
        priChatDao.setState(sm);
    }

    @Override
    public PageBean showHist(String smid, String currPage) throws SQLException, InvocationTargetException, IllegalAccessException {
        PageBean<Smlog> smlogPageBean = new PageBean<>();
        smlogPageBean.setCurrPage(Integer.parseInt(currPage));
        int pageSize = 10;
        smlogPageBean.setPageSize(pageSize);
        int smlogCount = priChatDao.findSmlogCount(smid);
        smlogPageBean.setTotalCount(smlogCount);
        double tcount = smlogCount;
        Double ceil = Math.ceil(tcount / pageSize);
        smlogPageBean.setTotalPage(ceil.intValue());
        int begin =(Integer.parseInt(currPage) - 1) * pageSize;
        List<Smlog> listBySmid = priChatDao.findListBySmid(smid, begin, pageSize);
        smlogPageBean.setList(listBySmid);
        return smlogPageBean;
    }

    @Override
    public List<Sm> checkState(UserInfo realUser) throws SQLException {
        return priChatDao.checkState(realUser);
    }

    @Override
    public Sm PCBySmid(String smid) throws IllegalAccessException, SQLException, InvocationTargetException {
        return priChatDao.PCBySmid(smid);
    }
}
