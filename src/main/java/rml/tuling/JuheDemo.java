package rml.tuling;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import rml.util.SignUtil;

/**
 * 问答机器人调用示例代码 － 聚合数据
 * 在线接口文档：http://www.juhe.cn/docs/112
 **/

public class JuheDemo
{
    
    // 配置您申请的KEY
    public static final String APPKEY = "6f4d0171e580cebea273330f60ab8bdf";
    
    // 1.问答
    public static String getRequest1(String info,String userId)
    {
        
        String result = null;
        String url = "http://op.juhe.cn/robot/index";// 请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();// 请求参数
        params.put("key", APPKEY);// 您申请到的本接口专用的APPKEY
        params.put("info", info);// 要发送给机器人的内容，不要超过30个字符
        params.put("dtype", "");// 返回的数据的格式，json或xml，默认为json
        params.put("loc", "");// 地点，如北京中关村
        params.put("lon", "");// 经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat", "");// 纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid", userId);// 1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        String res = "";
        try
        {
            result = SignUtil.net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if (object.getInt("error_code") == 0)
            {
                res = editResStr( object.get("result").toString());
                
            }
            else
            {
                res = object.get("error_code") + ":" + object.get("reason");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }
    
    public static String editResStr(String res)
    {
        JSONObject obj = new JSONObject(res);
        res = obj.get("text").toString();
        System.out.println("edit Befor" +res);
      res =  res.replaceAll("聚合数据", "ChuJ");
      res =  res.replaceAll("聚合", "ChuJ");
      res =   res.replaceAll("北京", "南京");
        System.out.println("edit After" +res);
        return res;
        
    }
    
    // 2.数据类型
    public static void getRequest2()
    {
        String result = null;
        String url = "http://op.juhe.cn/robot/code";// 请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();// 请求参数
        params.put("dtype", "");// 返回的数据格式，json或xml，默认json
        params.put("key", APPKEY);// 您申请本接口的APPKEY，请在应用详细页查询
        
        try
        {
            result = SignUtil.net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if (object.getInt("error_code") == 0)
            {
                System.out.println(object.get("result"));
            }
            else
            {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        getRequest1("","chu");
    }
    
    
    
   
}