package com.wuxing.chat.service.impl;


import com.wuxing.chat.dao.UserDao;
import com.wuxing.chat.service.UserService;
import org.apache.commons.dbutils.DbUtils;
import org.springframework.stereotype.Service;
import utils.BeanFactory;
import utils.MailUtils;
import utils.ObjectJDBCUtils;
import utils.UUIDUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wuxingx on 2017-1-22.
 */
@Service
public class UserServiceImp implements UserService {
    UserDao userDao = (UserDao) BeanFactory.getBean("UserDao");

    @Override
    public UserInfo login(User user) throws SQLException, InvocationTargetException, IllegalAccessException {
        return userDao.login(user);
    }

    @Override
    public void regist(User user, UserInfo userInfo) throws SQLException {
        //完善user里面的内容
        user.setId(UUIDUtils.getUUID());
        //完善userinfo里面的内容
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();

        userInfo.setCode(code);
        Chatlvp chatlvp = new Chatlvp();
        chatlvp.setPlv(1);
        userInfo.setChatlvp(chatlvp);
        userInfo.setShutup(0);
        Vip vip = new Vip();
        vip.setVip(0);
        userInfo.setVip(vip);
        userInfo.setImage("head/0.jpg");
        userInfo.setBan(0);
        try {
            ObjectJDBCUtils.startTransaction();
            userDao.registUser(user);
            userDao.registUserInfo(userInfo);
            DbUtils.commitAndCloseQuietly(ObjectJDBCUtils.getCurConnection());
        }catch (Exception e) {
            DbUtils.rollbackAndCloseQuietly(ObjectJDBCUtils.getCurConnection());
            e.printStackTrace();
            return;
        }
        MailUtils.sendMail(userInfo.getEmail(),code);
    }

    @Override
    public User checkName(String username) throws SQLException {
        return userDao.checkName(username);
    }

    @Override
    public UserInfo checkEmail(String email) throws SQLException {
        return userDao.checkEmail(email);

    }

    @Override
    public UserInfo active(String code) throws SQLException, InvocationTargetException, IllegalAccessException {
        return userDao.active(code);
    }

    @Override
    public void updateUser(UserInfo activeUser) throws SQLException {
        userDao.updateUser(activeUser);
    }

    @Override
    public UserInfo checkEmailUserinfo(String username, String email) throws SQLException {
        return userDao.checkEmailUserinfo(username,email);

    }

    @Override
    public void reSendEmail(UserInfo checkUser) throws SQLException {
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        checkUser.setCode(code);
        MailUtils.sendMail(checkUser.getEmail(),code);
        userDao.reSendEmail(checkUser);
    }

    @Override
    public List<Dirty> showDirty() throws SQLException {
        return userDao.showDirty();
    }
}
