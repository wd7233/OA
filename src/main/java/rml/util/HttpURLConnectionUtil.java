package rml.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import rml.pojo.TokenWx;

/**
 * 根据URL下载文件到本地
 */
public class HttpURLConnectionUtil
{
    private static final Logger LOGGER = Logger.getLogger(HttpURLConnectionUtil.class);
    
    
    public static String getTokenTest() throws JSONException, ParseException, IOException 
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type", "client_credential");
        map.put("appid", StringUtils.AppID);
        map.put("secret", StringUtils.AppSecret);
        JSONObject tokenJson = new JSONObject(HttpClientUtil.send(StringUtils.URL.TokenUrl, map, "utf-8"));
        LOGGER.info("TokenJson：" + "【" + tokenJson.toString() + "】"); 
        return tokenJson.getString("access_token");
            
    }
    // 获取Token
    public static String getToken()
        throws ParseException, IOException
    {
        
        TokenWx token  = null;
            Map<String, String> map = new HashMap<String, String>();
            map.put("grant_type", "client_credential");
            map.put("appid", StringUtils.AppIdFwh);
            map.put("secret", StringUtils.AppSecretFwh);
            JSONObject tokenJson = new JSONObject(HttpClientUtil.send(StringUtils.URL.TokenUrl, map, "utf-8"));
            LOGGER.info("TokenJson：" + "【" + tokenJson.toString() + "】");
            token = new TokenWx();
            token.setToken(tokenJson.getString("access_token"));
            token.setCreatetime(SignUtil.LongToDatetime(new Date().getTime()));
        return token.getToken();
        
    }
    
}
