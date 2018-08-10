package com.yecai.garden.soil.system.common.util;


import java.util.HashMap;
import java.util.Map;

/**
 * 返回工具类
 */
public class HttpReturnUtils {
    /**
     * 按状态返回
     * @param state
     * @return
     */
    public static Map<String, Object> returnMap(int state) {
        Map<String, Object> map=new HashMap<String, Object> ();
        String status="";
        String message="";
        String result="";
        if (state==0){
            status="201";
            message="fail";

        }else {
            status = "200";
            message = "success";
            result = "";
        }
        map.put("status", status);
        map.put("message", message);
        map.put("result", result);
        return map;
    }

    /**
     * 自定义返回的数据
     * @param status 状态码
     * @param message 消息
     * @param object 数据
     * @return map型结果
     */
    public static Map<String, Object> returnMap(String status,String message,Object object)  {
        Map<String, Object> map=new HashMap<String, Object> ();
        map.put("status", status);
        map.put("message", message);
        map.put("result", object);
        return map;
    }
    public static  Map<String, Object> returnMap(String status,String message,String objectName,Object object){
        Map<String, Object> map=returnMap(status,message,"");
        map.put(objectName,object);
        return map;
    }
    /**
     * 失败
     * @return 返回默认失败的状态和消息
     */
    public static Map<String,Object> returnFail() {
        return returnMap(0);
    }

    /**
     * 返回成功
     * @return 返回默认的成功和消息
     */
    public static Map<String,Object> returnSuccess() {
        return returnMap(1);
    }
}
