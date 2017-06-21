package com.wuxing.chat.utils;

import java.util.HashMap;

/**
 * Created by zx on 2017/6/14.
 */
public class Result {
    /**
     * @Desciption   成功状态码
     */
    public static final int STATUS_CODE_SUCESS=1;

    /**
     * @Desciption   错误状态码
     */
    public static final int STATUS_CODE_FAIL=0;
    /**
     * @Desciption 返回错误状态码和信息给客户端
     * @param      String
     */
    public static HashMap<String, Object> respErrorMsg(String msg) {
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put("status", STATUS_CODE_FAIL);
        result.put("code", STATUS_CODE_FAIL);
        result.put("msg",msg);
        System.out.println("返回的数据->\n"+result);
        return result;
    }
    /**
     * @Desciption 返回正确状态码和信息给客户端
     * @param      String
     */
    public static HashMap<String, Object> respSuccessMsg(String msg) {
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put("status", STATUS_CODE_SUCESS);
        result.put("code", STATUS_CODE_SUCESS);
        result.put("msg",msg);
        return result;
    }
    /**
     * @Desciption 返回正确状态码和信息给后台系统网页(带数据)
     * @param      String
     */
    @SuppressWarnings("rawtypes")
    public static HashMap<String, Object> respTableSuccessMsg(String msg,Page o) {
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put("status", STATUS_CODE_SUCESS);
        result.put("msg",msg);
        result.put("total",o.getTotal());
        result.put("rows", o.getResult());
        return result;
    }

    /**
     * @Desciption 返回正确状态码和信息给客户端(带数据)
     * @param      String
     */
    public static HashMap<String, Object> respSuccessMsg(String msg,Object o) {
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put("status", STATUS_CODE_SUCESS);
        result.put("msg",msg);
        result.put("data", o);
        return result;
    }

    /**
     * @Desciption 返回正确状态码和信息给客户端(带数据)
     * @param      String
     */
    public static HashMap<String, Object> respPageSuccessMsg(String msg,Integer limit,Integer totalPages,Integer totalRecords,Object o) {
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put("status", STATUS_CODE_SUCESS);
        result.put("msg",msg);
        result.put("limit",limit);
        result.put("totalPages",totalPages);
        result.put("totalRecords",totalRecords);
        result.put("data", o);
        return result;
    }
    /**
     * @Desciption 返回正确状态码和信息给客户端
     * @param      Object
     */
    public static HashMap<String, Object> respSuccessMsg(Object o) {
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put("status", STATUS_CODE_SUCESS);
        result.put("data", o);
        return result;
    }
}
