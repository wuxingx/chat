package com.wuxing.chat.controller;

import com.wuxing.chat.pojo.RoomRecord;
import com.wuxing.chat.pojo.ChatRoom;
import com.wuxing.chat.pojo.Dirty;
import com.wuxing.chat.pojo.UserInfo;
import com.wuxing.chat.service.ChatRoomService;
import com.wuxing.chat.utils.Utils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by wuxing on 2017-1-25.
 *
 */
@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController {

    @Autowired
    private ChatRoomService chatRoomService;


    public void init() throws ServletException {
        try {
            List<ChatRoom> chatRooms = chatRoomService.showList();
            for (ChatRoom chatRoom : chatRooms) {

                ArrayList<UserInfo> list = new ArrayList<>();
                //主键为聊天室bean
                rooms.put(chatRoom,list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Map<ChatRoom,List<UserInfo>> rooms = new LinkedHashMap<ChatRoom,List<UserInfo>>();
    //**********************页面***************************************
    //聊天室主页
    @RequestMapping("/home.wx")
    public String homePage(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        for(Map.Entry<ChatRoom,List<UserInfo>> room : rooms.entrySet()) {
            //rooms里面的list
            for (UserInfo userInfo : room.getValue()) {
                if(userInfo.getUser().getId().equals(realUser.getUser().getId())) {
                    room.getValue().remove(realUser);
                    break;
                }
            }
        }
        request.setAttribute("rooms",rooms);
        return "/jsp/chathome.jsp";
    }
    //聊天室
    public String chatRoom(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/chatroom.jsp";
    }
    //聊天室历史记录
    public String roomHistPage(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/roomhist.jsp";
    }
    //****************************************************************

    //在用户进入该聊天室 有密码的
    public String roomp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String password = request.getParameter("password");
        String cid = request.getParameter("cid");
        //查询聊天室bean
        ChatRoom chatRoom = chatRoomService.checkPassword(cid, password);
        if(chatRoom == null) {
            return "/ChatServlet/homePage";
        }
        //给rooms添加user
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        ArrayList<UserInfo> list = new ArrayList<>();
        boolean flag = true;
        for(Map.Entry<ChatRoom,List<UserInfo>> room : rooms.entrySet()) {
            if(room.getKey().getId().equals(chatRoom.getId())){
                //rooms里面的list
                for (UserInfo userInfo : room.getValue()) {
                    if(userInfo.getUser().getId().equals(realUser.getUser().getId())) {
                        //将指重设
                        flag=false;
                        break;
                    }
                }
                if(flag) {
                    room.getValue().add(realUser);
                }

            }else {
                Iterator<UserInfo> it = room.getValue().iterator();
                while (it.hasNext()) {
                    UserInfo next = it.next();
                    if(next.getUser().getId().equals(realUser.getUser().getId())) {
                        it.remove();
                    }
                }
            }

        }

        request.getSession().setAttribute("chatRoom",chatRoom);
        return request.getContextPath() + "/ChatServlet/chatRoom";
    }

    //在用户进入该聊天室 无密码的
    public String room(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException, IllegalAccessException, InvocationTargetException {
        String cid = request.getParameter("cid");
        //查询聊天室bean
        ChatRoom chatRoom = chatRoomService.findByCid(cid);
        if(chatRoom == null) {
            return "/ChatServlet/homePage";
        }
        //从session中获取已经登录的用户
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        boolean flag = true;
        for(Map.Entry<ChatRoom,List<UserInfo>> room : rooms.entrySet()) {
            if(room.getKey().getId().equals(chatRoom.getId())){
                //rooms里面的list
                for (UserInfo userInfo : room.getValue()) {
                    if(userInfo.getId().equals(realUser.getId())) {
                        //将指重设
                        flag=false;
                        break;
                    }
                }
                if(flag) {
                    room.getValue().add(realUser);
                }
            }else {
                Iterator<UserInfo> it = room.getValue().iterator();
                while (it.hasNext()) {
                    UserInfo next = it.next();
                    if(next.getUser().getId().equals(realUser.getUser().getId())) {
                        it.remove();
                    }
                }
            }
        }
        request.getSession().setAttribute("chatRoom",chatRoom);
        return request.getContextPath() + "/ChatServlet/chatRoom";
    }
    //聊天室列表

/*
    //异步请求的 将rooms存入session,页面请求这个方法就是重新rooms到session,重新获取人数
    public void poRooms(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setAttribute("rooms",rooms);
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        for(Map.Entry<String, List<UserInfo>> room : rooms.entrySet()) {
            map.put(room.getKey(),room.getValue().size() + "");
        }
        JSONArray jsonArray = JSONArray.fromObject(map);
        response.getWriter().write(jsonArray.toString());
    }
*/
    //异步查询聊天室密码是否正确
    public void checkPassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        ChatRoom chatRoom = chatRoomService.checkPassword(id, password);
        if(chatRoom != null) {
            response.getWriter().write("1");
        }
    }

    //异步查询当前在线列表
    public void userList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String cid = request.getParameter("cid");
        List<UserInfo> list = new ArrayList<>();
        for(Map.Entry<ChatRoom,List<UserInfo>> room : rooms.entrySet()) {
            if(room.getKey().getId().equals(cid)){
                //rooms里面的list
                list = room.getValue();
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.getWriter().write(jsonArray.toString());
    }
    //ban人
    public void banUser(HttpServletRequest request  , HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        Map<String,UserInfo> map = (Map<String,UserInfo>) this.getServletContext().getAttribute("onlineUser");
        map.remove(uid);
        for(Map.Entry<ChatRoom,List<UserInfo>> room : rooms.entrySet()) {
            Iterator<UserInfo> it = room.getValue().iterator();
            while (it.hasNext()) {
                UserInfo next = it.next();
                if(next.getUser().getId().equals(uid)) {
                    it.remove();
                }
            }
        }
        response.getWriter().write(1);
    }
    //发送消息
    public void sendMsg(HttpServletRequest request  , HttpServletResponse response) throws SQLException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("realUser");
        String msg = request.getParameter("msg");
        List<Dirty> dirties = (List<Dirty>) this.getServletContext().getAttribute("dirties");
        msg = Utils.checkDirty(msg, dirties);
        ChatRoom chatRoom = (ChatRoom) request.getSession().getAttribute("chatRoom");
        RoomRecord roomRecord = new RoomRecord();
        roomRecord.setUserInfoId(userInfo.getUserId());
        roomRecord.setChatRoom(chatRoom);
        roomRecord.setDate(new Date().toString());
        roomRecord.setMsg(msg);
        chatRoomService.sendMsg(roomLog);

    }


    //异步获取前30条消息
    public void showMsg(HttpServletRequest request  , HttpServletResponse response) throws SQLException, IOException, ParseException, IllegalAccessException, InvocationTargetException {
        response.setContentType("text/html;charset=utf-8");
        ChatRoom chatRoom = (ChatRoom) request.getSession().getAttribute("chatRoom");
        //查询前30条消息,完整的roomlog,包含userinfo,chatroom
        List<RoomLog> roomLogList = chatRoomService.showMsg(chatRoom.getId());
        if(roomLogList == null) {
            response.getWriter().write("0");
            return;
        }
        Collections.reverse(roomLogList);

        JSONArray jsonArray = JSONArray.fromObject(roomLogList);
        response.getWriter().write(jsonArray.toString());
    }
    //检查当前用户是否在onlineuser中
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        if(realUser == null) {
            response.getWriter().write("0");
        }
    }
    //查询历史记录的方法
    public String roomHist(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        String cid = request.getParameter("cid");
        String currPage = request.getParameter("currPage");
        PageBean<RoomLog> pageBean = chatRoomService.roomHist(cid, currPage);
        request.setAttribute("roomHist",pageBean);
        return "/ChatServlet/roomHistPage";
    }
    //用户退出
    public String userExit(HttpServletRequest request, HttpServletResponse response) {
        UserInfo realUser = (UserInfo) request.getSession().getAttribute("realUser");
        Map<String,UserInfo> map = (Map<String,UserInfo>) this.getServletContext().getAttribute("onlineUser");
        map.remove(realUser.getUser().getId());
        for(Map.Entry<ChatRoom,List<UserInfo>> room : rooms.entrySet()) {
            Iterator<UserInfo> it = room.getValue().iterator();
            while (it.hasNext()) {
                UserInfo next = it.next();
                if(next.getUser().getId().equals(realUser.getUser().getId())) {
                    it.remove();
                }
            }
        }
        request.getSession().removeAttribute("realUser");
        return request.getContextPath() + "/UserController/loginPage";
    }
}
