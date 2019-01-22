package rml.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * 类名: SignUtil </br>
 * 描述: 检验signature 工具类 </br>
 * 开发人员： souvc </br>
 * 创建时间： 2015-9-29 </br>
 * 发布版本：V1.0 </br>
 */
public class SignUtil
{
    
    // 与接口配置信息中的Token要一致
    private static String token = "ydjkzbh";
    
    /**
     * 方法名：checkSignature</br>
     * 详述：验证签名</br>
     * 开发人员：souvc</br>
     * 创建时间：2015-9-29 </br>
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @throws
     */
    public static final String DEF_CHATSET = "UTF-8";
    
    public static final int DEF_CONN_TIMEOUT = 30000;
    
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    
    public static boolean checkSignature(String signature, String timestamp, String nonce)
    {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[] {token, timestamp, nonce};
        Arrays.sort(arr);
        
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++)
        {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try
        {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        
        content = null;
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }
    
    /**
     * 方法名：byteToStr</br>
     * 详述：将字节数组转换为十六进制字符串</br>
     * 开发人员：souvc </br>
     * 创建时间：2015-9-29 </br>
     * @param byteArray
     * @return
     * @throws
     */
    private static String byteToStr(byte[] byteArray)
    {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++)
        {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    
    /**
     * 方法名：byteToHexStr</br>
     * 详述：将字节转换为十六进制字符串</br>
     * 开发人员：souvc</br>
     * 创建时间：2015-9-29 </br>
     * @param mByte
     * @return
     * @throws
     */
    private static String byteToHexStr(byte mByte)
    {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
    public static String LongToDatetime(Long time) 
    {
        String strDate = "";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = new Date(time);  
        strDate = sdf.format(date);
        return strDate;
    }
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map<String, Object> params, String method)
        throws Exception
    {
        Logger logger = Logger.getLogger("op");
        System.out.println(params.toString());
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try
        {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET"))
            {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection)url.openConnection();
            if (method == null || method.equals("GET"))
            {
                conn.setRequestMethod("GET");
            }
            else
            {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST"))
            {
                try
                {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null)
            {
                sb.append(strRead);
            }
            rs = sb.toString();
            logger.info("输出rs" + rs);
            System.out.println(rs);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
            if (conn != null)
            {
                conn.disconnect();
            }
        }
        return rs;
    }
    
    // 将map型转为请求参数型
    public static String urlencode(Map<String, Object> data)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet())
        {
            try
            {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    
    
    public static boolean isConstell(String flag)
    {
        List<String> constells = new ArrayList<String>();
        constells.add("水瓶座");
        constells.add("双鱼座");
        constells.add("白羊座");
        constells.add("金牛座");
        constells.add("双子座");
        constells.add("巨蟹座");
        constells.add("狮子座");
        constells.add("处女座");
        constells.add("摩羯座");
        constells.add("天秤座");
        constells.add("天蝎座");
        constells.add("射手座");
        for (String constell : constells)
        {
            if (flag.equals(constell))
            {
                return true;
            }
        }
        return false;
    }
    
    public static String getConstellType(String type)
    {
        String reuslt= "today";
        if (type.indexOf("明") != -1)
        {
            reuslt = "tomorrow";
        }
        if (type.indexOf("这周") != -1 || type.indexOf("本周") != -1)
        {
            reuslt = "week";
        }
        if (type.indexOf("下周") != -1)
        {
            reuslt = "nextweek";
        }
        if (type.indexOf("月") != -1)
        {
            reuslt = "month";
        }
        if (type.indexOf("今年") != -1)
        {
            reuslt = "year";
        }
        return reuslt;
    }
    
    public static String getConstellMsg(String result)
    {
        JSONObject js = new JSONObject(result);
        StringBuffer sb = new StringBuffer();
        sb.append("星  座  ：");
        sb.append(js.get("name") + "\n");
        sb.append("日  期  ：");
        sb.append(js.get("datetime") + "\n");
        sb.append("综合指数：");
        sb.append(js.get("all") + "\n");
        sb.append("幸运色  ：");
        sb.append(js.get("color") + "\n");
        sb.append("健康指数：");
        sb.append(js.get("health") + "\n");
        sb.append("爱情指数：");
        sb.append(js.get("love") + "\n");
        sb.append("财运指数：");
        sb.append(js.get("money") + "\n");
        sb.append("幸运数字：");
        sb.append(js.get("number") + "\n");
        sb.append("速配星座：");
        sb.append(js.get("QFriend") + "\n");
        sb.append("工作指数：");
        sb.append(js.get("work") + "\n");
        sb.append("今日概述：");
        sb.append(js.get("summary"));
        return sb.toString();
    }

}