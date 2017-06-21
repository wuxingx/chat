package com.wuxing.chat.utils;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wuxing on 2017-1-8.
 */
public class BeanFactory {



    public static Object getBean(String id) {
        //解析applicationContext.xml
        //创建解析器
        SAXReader reader = new SAXReader();

        try {
            //获取class目录  获得文件对象
            Document document = reader.read(BeanFactory.class.getClassLoader().getResourceAsStream("applicationContext.xml"));
            //所有bean下面id等与id的值
            Element element = (Element) document.selectSingleNode("//bean[@id='" + id + "']");
            //获取class属性
            String beanClass = element.attributeValue("class");
            //反射获得类
            Class clazz = Class.forName(beanClass);
            //返回类的实例
            final Object o = clazz.newInstance();
            if(id.endsWith("dao")){
                Object proxyInstance = Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("sendMsg")) {
                            //说明是一个数据库保存的sql语句
                            //目前未对权限进行处理

                            return method.invoke(o, args);
                        }
                        return method.invoke(o, args);
                    }
                });
                return proxyInstance;
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
