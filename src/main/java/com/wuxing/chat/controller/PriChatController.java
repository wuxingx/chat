package com.wuxing.chat.controller;

import com.wuxing.chat.service.PriChatService;
import net.sf.json.JSONArray;
import utils.BaseServlet;
import utils.BeanFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by wuxing on 2017-2-1.
 */
@WebServlet(name = "PriChatController",value = "/PriChatController/*")
public class PriChatController extends BaseServlet {
    PriChatService priChatService = (PriChatService) BeanFactory.getBean("PriChatService");

    /******************页面*****************************************/
    public String pcroomPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/pcroom.jsp";
    }
    public String pcHistPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/pcHist.jsp";
    }


    /*****************servlet**************************************/
    public String priChat(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        //获取发起人
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        String uid = request.getParameter("uid");
        //查询私聊的表 查询是否存在 不存在就创建
        Sm sm = priChatService.findPC(realUser, uid);
        if(!sm.getUserInfo1().getUser().getId().equals(realUser.getUser().getId())) {
            //要userinfo1为当前用户
            UserInfo temp = sm.getUserInfo1();
            sm.setUserInfo1(realUser);
            sm.setUserInfo2(temp);
        }
        if(sm.getState() != null && realUser.getUser().getId().equals(sm.getState()) ) {
            //如果state未读状态的内的用户id equals上当前该用户,就把state置为null
            priChatService.changeState(sm);
        }
        request.getSession().setAttribute("pcroom",sm);
        return request.getContextPath() + "/PriChatController/pcroomPage";
    }
    //异步查询聊天记录
    public void showMsg(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //通过私聊的表去查询最近10条聊天记录
        Sm sm = (Sm) request.getSession().getAttribute("pcroom");
        List<Smlog> msg = priChatService.findMsg(sm.getId());
        if(msg == null) {
            response.getWriter().write("0");
            return;
        }
        Collections.reverse(msg);
        JSONArray jsonArray = JSONArray.fromObject(msg);
        response.getWriter().write(jsonArray.toString());
    }
    //异步校验用户是否在线
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        if(realUser == null) {
            response.getWriter().write("0");
        }
    }
    //发送消息
    public void sendMsg(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("realUser");
        String msg = request.getParameter("msg");
        List<Dirty> dirties = (List<Dirty>) this.getServletContext().getAttribute("dirties");
        for (int o = 0 ;o < dirties.size(); o++) {
            if(msg.contains(dirties.get(o).getWord())) {
                int size = dirties.get(o).getWord().length();
                int i = msg.indexOf(dirties.get(o).getWord());
                StringBuffer star = new StringBuffer();
                for (int j = 0; j < size; j++) {
                    star.append("*");
                }
                String substring = msg.substring(0, i);
                String substring1 = msg.substring(i + size, msg.length());
                msg = substring + star.toString() + substring1;
                o--;
            }
        }
        Sm sm = (Sm) request.getSession().getAttribute("pcroom");
        Smlog smlog = new Smlog();
        smlog.setSendUser(sm.getUserInfo1());
        smlog.setMsg(msg);
        smlog.setSm(sm);
        priChatService.sendMsg(smlog);
        //将sm的state状态设置为对方的id
        priChatService.setState(sm);
    }
    //查看历史消息
    public String smpcHist(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        String smid = request.getParameter("smid");
        String currPage = request.getParameter("currPage");
        PageBean<Smlog> pageBean = priChatService.showHist(smid, currPage);
        request.setAttribute("smpcHist",pageBean);
        return "/PriChatController/pcHistPage";
    }
    //异步查询是否有未读消息
    public void checkState(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        if(realUser != null) {
            List<Sm> sms = priChatService.checkState(realUser);
            if(sms != null) {
                response.getWriter().write("1");
            }
        }

    }

    //通过sm的id打开私聊
    public String PCBySmid(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        String smid = request.getParameter("smid");
        Sm sm = priChatService.PCBySmid(smid);
        if(!sm.getUserInfo1().getUser().getId().equals(realUser.getUser().getId())) {
            //要userinfo1为当前用户
            UserInfo temp = sm.getUserInfo1();
            sm.setUserInfo1(realUser);
            sm.setUserInfo2(temp);
        }
        if(sm.getState() != null && realUser.getUser().getId().equals(sm.getState()) ) {
            //如果state未读状态的内的用户id equals上当前该用户,就把state置为null
            priChatService.changeState(sm);
        }
        request.getSession().setAttribute("pcroom",sm);
        return request.getContextPath() + "/PriChatController/pcroomPage";
    }
}
