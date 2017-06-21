package com.wuxing.chat.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wuxing on 2017-1-4.
 * 字符编码自动转换工具类
 */
@WebFilter(filterName = "aEncodingFilter",value = "/*")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, final ServletResponse resp, final FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        //增强request
        //传入被增强对象的.class和这个对象的所有实现的接口
        HttpServletRequest myRequest = (HttpServletRequest)Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //获取当前方法的方法名
                        String methodName = method.getName();
                        if (methodName.equals("getParameter")) {
                            //获取method的提交方式
                            String type = request.getMethod();
                            if (type.equalsIgnoreCase("get")) {
                                //让方法执行,返回一个String 等同于:request是执行对象,args是参数
                                //request.getParameter(args)
                                String value = (String) method.invoke(request, args);
                                value = new String(value.getBytes("ISO-8859-1"), "utf-8");
                                return value;
                            } else if (type.equalsIgnoreCase("post")) {
                                request.setCharacterEncoding("utf-8");
                            }
                        }
                        return method.invoke(request, args);
                    }
                });
        chain.doFilter(myRequest, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
