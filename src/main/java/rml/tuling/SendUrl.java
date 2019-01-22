package rml.tuling;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import rml.util.SignUtil;

public class SendUrl
{
    private static String appid = "wx20eb00996b8eed48";
    private static String secret = "b1577254cbe912d2539427beb43ba38b";
    /**
     * 根据appid和secret 获取AccessToken
     * */
    public static String getAccessToken() throws Exception 
    {
        String AccesssTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("grant_type", "client_credential");
        params.put("appid", appid);
        params.put("secret", secret);
       String jsonStr =  SignUtil.net(AccesssTokenUrl, params, "GET");
       JSONObject obj = new JSONObject(jsonStr);
       return obj.getString("access_token");
    }
    /**
     * 获取用户详细信息
     * */
    public static JSONObject getUserDetail(String openId) throws Exception
    {
       
       String getUserDetailUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("access_token", SendUrl.getAccessToken());
       params.put("openid", openId);
       String jsonStr =  SignUtil.net(getUserDetailUrl, params, "GET");
       JSONObject obj = new JSONObject(jsonStr);
       return obj;
    }
    
}
