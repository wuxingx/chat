package com.wuxing.chat.interceptor;

import com.sun.org.apache.xalan.internal.xsltc.dom.Filter;
import com.wuxing.chat.javaBean.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class PrivilegeFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Map<String,UserInfo> map = (Map<String,UserInfo>) request.getServletContext().getAttribute("onlineUser");
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        if(realUser == null) {
            request.getSession().setAttribute("msg","请登陆后再进行操作");
            response.sendRedirect(request.getContextPath() + "/UserController/usermsg");
            return false;
        }
        if(!map.containsKey(realUser.getUser().getId())) {
            Cookie cookie = new Cookie("autoLogin",null);
            cookie.setPath(request.getContextPath());
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            request.getSession().setAttribute("realUser",null);
            request.setAttribute("msg","请登陆后再进行操作");
            request.getRequestDispatcher("/UserServlet/usermsg").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
