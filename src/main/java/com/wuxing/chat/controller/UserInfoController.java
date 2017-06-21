package com.wuxing.chat.controller;

import com.wuxing.chat.javaBean.PageBean;
import com.wuxing.chat.javaBean.Sm;
import com.wuxing.chat.javaBean.UserInfo;
import com.wuxing.chat.service.UserInfoService;
import utils.BaseServlet;
import utils.BeanFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by wuxing on 2017-2-1.
 */
@WebServlet(name = "UserInfoController",value = "/UserInfoController/*")
public class UserInfoController extends BaseServlet {
    UserInfoService userInfoService = (UserInfoService) BeanFactory.getBean("UserInfoService");
    /**************页面****************************/
/*
    public String smListPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/smList.jsp";
    }
    public String userPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/user.jsp";
    }
    public String editUserPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/edituser.jsp";
    }
    public String changePWPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/changePW.jsp";
    }
*/

    /****************servlet*******************/
    //当前用户的sm私聊列表
    public String smList(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        String currPage = request.getParameter("currPage");
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        PageBean<Sm> smList = userInfoService.findSmList(realUser,currPage);
        request.setAttribute("smList",smList);
        return "/UserInfoController/smListPage";
    }
    //修改密码
    public String changePW(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        String oldpw = request.getParameter("oldpw");
        String password = request.getParameter("password");
        //先查看密码是否正确
        if(!realUser.getUser().getPassword().equals(oldpw)) {
            //错误
            request.setAttribute("msg","<span style='color:red'><b>原密码错误</b></span>");
            return "/UserInfoController/changePWPage";
        }
        realUser.getUser().setPassword(password);
        userInfoService.changePW(realUser);
        return "/UserInfoController/userPage";
    }
    // 修改头像

}
