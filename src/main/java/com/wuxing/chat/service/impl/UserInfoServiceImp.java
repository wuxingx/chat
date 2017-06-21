package com.wuxing.chat.service.impl;

import com.wuxing.chat.dao.UserInfoDao;
import com.wuxing.chat.javaBean.PageBean;
import com.wuxing.chat.javaBean.Sm;
import com.wuxing.chat.javaBean.UserInfo;
import com.wuxing.chat.service.UserInfoService;
import org.springframework.stereotype.Service;
import utils.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuxing on 2017-2-1.
 */
@Service
public class UserInfoServiceImp implements UserInfoService {
    UserInfoDao userInfoDao = (UserInfoDao) BeanFactory.getBean("UserInfoDao");

    @Override
    public PageBean<Sm> findSmList(UserInfo realUser,String currPage) throws IllegalAccessException, SQLException, InvocationTargetException {
        PageBean<Sm> smlogPageBean = new PageBean<>();
        smlogPageBean.setCurrPage(Integer.parseInt(currPage));
        int pageSize = 10;
        smlogPageBean.setPageSize(pageSize);
        int smlogCount = userInfoDao.findSmlogCount(realUser);
        smlogPageBean.setTotalCount(smlogCount);
        double tcount = smlogCount;
        Double ceil = Math.ceil(tcount / pageSize);
        smlogPageBean.setTotalPage(ceil.intValue());
        int begin =(Integer.parseInt(currPage) - 1) * pageSize;
        List<Sm> smList = userInfoDao.findSmList(realUser, begin, pageSize);
        smlogPageBean.setList(smList);
        return smlogPageBean;

    }

    @Override
    public void editUserInfo(UserInfo realUser) throws SQLException {
        userInfoDao.editUserInfo(realUser);
    }

    @Override
    public void changePW(UserInfo realUser) throws SQLException {
        userInfoDao.changePW(realUser);
    }
}
