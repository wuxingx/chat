package com.wuxing.chat.controller;

import com.wuxing.chat.javaBean.UserInfo;
import com.wuxing.chat.service.UserInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuxing on 2017-1-12.
 */
@WebServlet(name = "EditUserInfoController",value = "/EditUserInfoController")
public class EditUserInfoController extends HttpServlet {
    UserInfoService userInfoService = (UserInfoService) BeanFactory.getBean("UserInfoService");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //设置缓存区的大小 如果没超过这个大小 文件会暂存到内存,超过了就会产生临时文件
            diskFileItemFactory.setSizeThreshold(3 * 1024 * 1024);
            // 设置临时文件存放的路径:
            // diskFileItemFactory.setRepository(repository);

            // 获得核心解析类:ServletFileUpload
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            fileUpload.setHeaderEncoding("UTF-8");
            //解析request请求 返回fileitem对相集合
            List<FileItem> list = fileUpload.parseRequest(request);
            HashMap<String, String> map = new HashMap<>();
            UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
            String fileName = "";
            for (FileItem fileItem : list) {
                if(fileItem.isFormField()){
                    //如果是表单属性,就获取键值对存入map
                    String name = fileItem.getFieldName();
                    String string = fileItem.getString("UTF-8");
                    map.put(name,string);
                }else {
                    //说明是文件上传
                    //获取文件名
                    String name = fileItem.getName();
                    int i = name.lastIndexOf(".");
                    String substring = name.substring(i, name.length());
                    fileName = realUser.getUser().getId() + substring;

                    //获取文件输入流
                    InputStream inputStream = fileItem.getInputStream();
                    //从servlet域中获取绝对路径 在发布的tomcat里面
                    String realPath = getServletContext().getRealPath("/head");
                    //存储到...路径下的/filename
                    FileOutputStream fileOutputStream = new FileOutputStream(realPath + "/" + fileName);
                    IOUtils.copy(inputStream,fileOutputStream);
                }
            }
            //封装数据
            BeanUtils.populate(realUser,map);
            realUser.setImage("/head/" + fileName);

            //存入到数据库
            userInfoService.editUserInfo(realUser);

            response.sendRedirect(request.getContextPath() + "/UserInfoController/userPage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
