package rml.util;


import java.util.Date;  
/** 
* 封装最终的xml格式结果 
* @author pamchen-1 
* 
*/  
public class FormatXmlProcess {  
  /** 
   * 封装文字类的返回消息 
   * @param to 
   * @param from 
   * @param content 
   * @return 
   */  
  public String formatXmlAnswer(String to, String from, String content) {  
      StringBuffer sb = new StringBuffer();  
      Date date = new Date();  
      sb.append("<xml><ToUserName><![CDATA[");  
      sb.append(to);  
      sb.append("]]></ToUserName><FromUserName><![CDATA[");  
      sb.append(from);  
      sb.append("]]></FromUserName><CreateTime>");  
      sb.append(date.getTime());  
      sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
      sb.append(content);  
      sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
      return sb.toString();  
  }  
  public String formatXmlVoice(String to, String from, String content) 
  {
      StringBuffer sb = new StringBuffer();  
      Date date = new Date();  
      sb.append("<xml><ToUserName><![CDATA[");  
      sb.append(to);  
      sb.append("]]></ToUserName><FromUserName><![CDATA[");  
      sb.append(from);  
      sb.append("]]></FromUserName><CreateTime>");  
      sb.append(date.getTime());  
      sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
      sb.append(content);  
      sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
      return sb.toString();  
  }

  public String formatXmlImage(String to, String from, String mediaId) 
  {
      StringBuffer sb = new StringBuffer();  
      Date date = new Date();  
      sb.append("<xml><ToUserName><![CDATA[");  
      sb.append(to);  
      sb.append("]]></ToUserName><FromUserName><![CDATA[");  
      sb.append(from);  
      sb.append("]]></FromUserName><CreateTime>");  
      sb.append(date.getTime());  
      sb.append("</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[");  
      sb.append(mediaId);  
      sb.append("]]></MediaId></Image></xml>");  
      return sb.toString();  
  }
//  public String formatXmlNews(String to, String from, List<News> news ) 
//  {
////      <ArticleCount>2</ArticleCount>
////      <Articles>
////      <item>
////      <Title><![CDATA[title1]]></Title> 
////      <Description><![CDATA[description1]]></Description>
////      <PicUrl><![CDATA[picurl]]></PicUrl>
////      <Url><![CDATA[url]]></Url>
////      </item>
////      <item>
////      <Title><![CDATA[title]]></Title>
////      <Description><![CDATA[description]]></Description>
////      <PicUrl><![CDATA[picurl]]></PicUrl>
////      <Url><![CDATA[url]]></Url>
////      </item>
////      </Articles>
////      </xml> 
//     News newOne  = news.get(0);
//      StringBuffer sb = new StringBuffer();  
//      Date date = new Date();  
//      sb.append("<xml><ToUserName><![CDATA[");  
//      sb.append(newOne.getOpenid());  
//      sb.append("]]></ToUserName><FromUserName><![CDATA[");  
//      sb.append(from);  
//      sb.append("]]></FromUserName><CreateTime>");  
//      sb.append(date.getTime());  
//      sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount><![CDATA[");  
//      sb.append(news.size());  
//      sb.append("]]></ArticleCount><Articles><item><Title><![CDATA[");
//      sb.append(newOne.getTitle());
//      sb.append("]]></Title><Description><![CDATA[");
//      sb.append(newOne.getDescription());
//      sb.append("]]></Description><PicUrl><![CDATA[");
//      sb.append(newOne.getPicurl());
//      sb.append("]]></PicUrl><Url><![CDATA[");
//      sb.append(newOne.getUrl());
//      sb.append("]]></Url></item></Articles></xml> ");
//      return sb.toString();  
//  }
}  