package com.wuxing.chat.controller;

import com.wuxing.chat.javaBean.Dirty;
import com.wuxing.chat.javaBean.User;
import com.wuxing.chat.javaBean.UserInfo;
import com.wuxing.chat.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import utils.BaseServlet;
import utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "UserController",value = "/UserController/*")
public class UserController extends BaseServlet {

    @Override
    public void init() throws ServletException {
        Map<String,UserInfo> map = new HashMap<String,UserInfo>();
        UserInfo userInfo = new UserInfo();
        User user = new User();
        user.setId("test");
        userInfo.setUser(user);
        map.put(userInfo.getUser().getId(),userInfo);
        this.getServletContext().setAttribute("onlineUser",map);
        try {
            List<Dirty> dirties = userService.showDirty();
            this.getServletContext().setAttribute("dirties",dirties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    UserService userService = (UserService) BeanFactory.getBean("UserService");
    //*****************页面*******************************
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        if(realUser == null) {
            return "/jsp/login.jsp";
        }else {
            return request.getContextPath() + "/ChatServlet/homePage";
        }
    }
    public String registPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/regist.jsp";
    }
    public String userPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/user.jsp";
    }
    //重定向重新激活邮件界面
    public String activeEmailPage(HttpServletRequest request, HttpServletResponse response) {
        return request.getContextPath() + "/jsp/activeemail.jsp";
    }
    //重定向msg页面
    public String usermsg(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/usermsg.jsp";
    }

    //******************处理*****************************

    //异步校验regsit验证码
    public void registCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkCode = request.getParameter("checkCode");
        String checkCode1 = (String)request.getSession().getAttribute("code");
        if(!checkCode1.equalsIgnoreCase(checkCode)) {
            //request.setAttribute("checkCodeError","<span class='formtips onError'>验证码输入有误</span>");
            response.getWriter().write(1);
        }
    }

    //异步校验用户名是否存在
    public String checkName(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        User user = userService.checkName(username);
        if(user == null) {
            //不存在用户名
            response.getWriter().write("0");
        }
        return null;
    }

    //异步校验邮箱是否存在
    public String checkEmail(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String email = request.getParameter("email");
        UserInfo user = userService.checkEmail(email);
        if(user == null) {
            //不存在邮箱
            response.getWriter().write("0");
        }
        return null;
    }
    //注册
    public String regist(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {
        response.setContentType("text/html;charset = utf-8");
        String uuid = request.getParameter("uuid");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        UserInfo userInfo = new UserInfo();
        BeanUtils.populate(user,parameterMap);
        BeanUtils.populate(userInfo,parameterMap);
        userInfo.setUser(user);
        userService.regist(user,userInfo);
        request.getSession().setAttribute("msg","注册成功,请去邮箱激活");
        request.getSession().setAttribute("email",request.getParameter("email"));
        return request.getContextPath() + "/UserController/usermsg";
    }
    //登录
    public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        String autoWrite = request.getParameter("autoWrite");
        UserInfo loginUser = userService.login(user);

        if (loginUser == null) {
            //登录信息有误
            request.setAttribute("msg", "<span style='color:red'><b>账号或密码错误</b></span>");
            return "/UserController/loginPage";
        }else if(loginUser.getBan() != 0) {
            request.setAttribute("msg","<span style='color:red'><b>您的账号已被禁封!</b></span>");
            return "/UserController/loginPage";
        }else if (loginUser.getCode() != null) {
            request.setAttribute("msg","您的账号还未激活,<a href='" + request.getContextPath() + "/UserController/activeEmailPage" +"'>点击此处激活</a>");
            return "/UserController/loginPage";
        }else {
            if("true".equals(autoWrite)) {
                //说明记住密码被勾选
                Cookie cookie = new Cookie("autoWrite", user.getUsername() + "#" + user.getPassword());
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(60 * 60 *24 * 7);
                response.addCookie(cookie);
            }
            String autoLogin = request.getParameter("autoLogin");
            if("true".equals(autoLogin)) {
                //说明自动登录被勾选
                Cookie cookie = new Cookie("autoLogin", username + "#" + password);
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            }
            Map<String,UserInfo> list = (Map<String,UserInfo>) this.getServletContext().getAttribute("onlineUser");
            list.put(loginUser.getUser().getId(),loginUser);
            request.getSession().setAttribute("realUser",loginUser);
            //跳转到聊天室界面
            return request.getContextPath() + "/ChatServlet/homePage";
        }
    }


    //接收激活邮件
    public String active(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        response.setContentType("text/html;charset=utf-8");
        //获得激活码
        String code = request.getParameter("code");
        //没有user就是激活码错误
        UserInfo activeUser = userService.active(code);
        if(activeUser == null){
            //说明激活失败
            request.getSession().setAttribute("msg","激活失败,请重试!");
        }else {
            //将激活后的user重新传入数据库
            activeUser.setCode(null);
            userService.updateUser(activeUser);
            request.getSession().setAttribute("msg","激活成功!");
            request.getSession().removeAttribute("email");
        }
        return request.getContextPath() + "/UserController/usermsg";
    }

    //重发激活邮件
    public String activeEmail(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {
        //验证输入的用户名和邮箱是否正确
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        UserInfo checkUser = userService.checkEmailUserinfo(username,email);
        if(checkUser == null) {
            //说明用户名和eamli的组合不对
            request.setAttribute("msg","用户名或邮箱错误");
            return "/html/activeemail.jsp";
        }else if(checkUser.getCode() == null){
            request.setAttribute("msg","该账户已被激活");
            return "/html/activeemail.jsp";
            //正确就重新发送激活邮件,替换掉数据库中改用户的激活码
        }else {
            userService.reSendEmail(checkUser);
            request.getSession().setAttribute("email",checkUser.getEmail());
            return request.getContextPath() + "/UserController/usermsg";
        }
    }

}
