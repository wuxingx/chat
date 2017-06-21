package com.wuxing.chat.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by wuxing on 2017-1-4.
 *
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取从路径的最后一个字符往前数直到遇到斜杠/为止的字符串
        String methodName = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        Class clazz = this.getClass();
        try {
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            String path = (String)method.invoke(this, req, resp);
            if(path != null && path != "") {
                //如过path里面包含项目名就说明路径是带项目名的全路径,就是转发
                String contextPath = req.getContextPath();
                if(path.contains(req.getContextPath())) {
                    resp.sendRedirect(path);
                    //否者就说明是转发
                } else {
                    req.getRequestDispatcher(path).forward(req,resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
