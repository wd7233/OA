package rml.util;

public interface StringUtils
{
    final public static int MaxTalkCont  = 21;
    final public static String AppID = "wx20eb00996b8eed48";
    final public static String AppSecret = "b1577254cbe912d2539427beb43ba38b";
    final public static String AppIdFwh = "wx199a341daf297b85";
    final public static String AppSecretFwh = "7004300760fe5032011228aa7104697b";
    interface URL 
    {
        String UserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";
        String TokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
        String UserListUrl = "https://api.weixin.qq.com/cgi-bin/user/get";
        String SendUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
        String getCode = "https://api.weixin.qq.com/sns/oauth2/access_token";
    }
    interface API
    {
        // 设置APPID/AK/SK
        public static final String Baidu_APP_ID = "10499621";
        
        public static final String Baidu_API_KEY = "HHEh36koKdGC0m4pdGttaZSz";
        
        public static final String Baidu_SECRET_KEY = "CrNaSY8ShiGbShFB2WRMIzIKrBYdsi16 ";
    }
        
}
