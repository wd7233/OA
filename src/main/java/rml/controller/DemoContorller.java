package rml.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rml.pojo.ReceiveXmlEntity;
import rml.tuling.JuheDemo;
import rml.util.FormatXmlProcess;
import rml.util.ReceiveXmlProcess;
import rml.util.SignUtil;

/**
 * 微信服务端收发消息接口
 * 
 * @author pamchen-1
 * 
 */
@Controller
@RequestMapping(value = {"Demo"})
public class DemoContorller
{
    
    @RequestMapping("/init")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ParseException
    {
        if ("GET".equals(request.getMethod()))
        {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            
            PrintWriter out = response.getWriter();
            
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce))
            {
                out.print(echostr);
            }
            
            out.close();
            out = null;
        }
        else
        {
            
            /** 读取接收到的xml消息 */
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            String result = "";
            /** 解析xml数据 */
            ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(sb.toString());
//            if ("event".endsWith(xmlEntity.getMsgType())&&"subscribe".endsWith(xmlEntity.getEvent()))
//            {
//                subscribeService.addUser(xmlEntity);
//                return ;
//                
//            }
            if ("text".endsWith(xmlEntity.getMsgType()))
            {
              result = JuheDemo.getRequest1(xmlEntity.getContent(),xmlEntity.getToUserName());
              result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
              //情侣链接
               // talkService.talkContent(xmlEntity);  
            }
//            if("image".endsWith(xmlEntity.getMsgType()))
//            {
//            result = returnService.savePicture(xmlEntity);
//            }
//            if("location".endsWith(xmlEntity.getMsgType())) 
//            {
//            result = returnService.LocationReturn(xmlEntity);
//            }
//            if("voice".endsWith(xmlEntity.getMsgType())) 
//            {
//                result = returnService.VoiceReturn(xmlEntity);
//              
//            }
           try
            {
                OutputStream os = response.getOutputStream();
                os.write(result.getBytes("UTF-8"));
                os.flush();
                os.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }
    
}
