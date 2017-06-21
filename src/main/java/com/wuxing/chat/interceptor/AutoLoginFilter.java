package com.wuxing.chat.interceptor;

import com.wuxing.chat.javaBean.User;
import com.wuxing.chat.javaBean.UserInfo;
import com.wuxing.chat.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import utils.BeanFactory;
import utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class AutoLoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //自动登录
        try {
            //查看session
            UserInfo autoLoginUser = (UserInfo) request.getSession().getAttribute("realUser");
            UserInfo login = null;
            if (autoLoginUser != null) {
                //session里面有
                return true;
            } else {
                //查看cookie
                Cookie[] cookies = request.getCookies();
                Cookie autoLogin = CookieUtils.cookie(cookies, "autoLogin");
                if (autoLogin == null) {
                    //说明cookie没有
                    return true;
                } else {
                    String value = autoLogin.getValue();
                    String username = value.split("#")[0];
                    String password = value.split("#")[1];
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    UserService userService = (UserService) BeanFactory.getBean("UserService");
                    login = userService.login(user);
                    if (login == null) {
                        //说明本地cookie被篡改
                        return true;
                    } else {
                        request.getSession().setAttribute("realUser", login);
                        return true;
                    }
                }
            }
            if (login != null) {
                Map<String, UserInfo> map = (Map<String, UserInfo>) request.getServletContext().getAttribute("onlineUser");
                map.put(login.getUser().getId(), login);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
