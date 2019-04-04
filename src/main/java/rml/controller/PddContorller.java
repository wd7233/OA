package rml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rml.model.Express;
import rml.pojo.ReceiveXmlEntity;
import rml.service.ExpressServiceI;
import rml.util.FormatXmlProcess;
import rml.util.ReceiveXmlProcess;
import rml.util.SignUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 拼多多开放平台接口
 * @author pamchen-1
 */
@Controller
@RequestMapping(value = {"Pdd"})
public class PddContorller
{
    
    @Autowired
    private ExpressServiceI expressService;
    
    @RequestMapping("/init")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try
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
                System.out.println(sb.toString());
                if ("event".endsWith(xmlEntity.getMsgType()) && "subscribe".endsWith(xmlEntity.getEvent()))
                {
                    return;
                    
                }
                if ("text".endsWith(xmlEntity.getMsgType()))
                {
                    // result = JuheDemo.getRequest1(xmlEntity.getContent(), xmlEntity.getToUserName());
                    result = this.selectExpress(xmlEntity.getContent());
                    result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
                    // 情侣链接
                    // talkService.talkContent(xmlEntity);
                }
                // if("image".endsWith(xmlEntity.getMsgType()))
                // {
                // result = returnService.savePicture(xmlEntity);
                // }
                if ("location".endsWith(xmlEntity.getMsgType()))
                {
                    // result = returnService.LocationReturn(xmlEntity);
                    String x = xmlEntity.getLocation_X();
                    String y = xmlEntity.getLocation_Y();
                    System.out.println(x);
                    System.out.println("==========================");
                    System.out.println(y);
                }
                // if("voice".endsWith(xmlEntity.getMsgType()))
                // {
                // result = returnService.VoiceReturn(xmlEntity);
                //
                // }
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
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    private String selectExpress(String content)
    {
        System.out.println(content);
        String province = content.split("，")[0];
        String weight = content.split("，")[1];
        String str = "";
        Express baishiExpress = expressService.selectPrice(weight, province, 0);
        Express youzhengExpress = expressService.selectPrice(weight, province, 1);
        // Express shengtongExpress = expressService.selectPrice(weight, province, 2);
        Express yuantongExpress = expressService.selectPrice(weight, province, 3);
        String yzPrice =  youzhengExpress == null ? "-" : youzhengExpress.getPrice() ;
        String bsPrice =  baishiExpress == null ? "-" : baishiExpress.getPrice();
        String ytPrice = yuantongExpress == null ? "-" : yuantongExpress.getPrice();
        str += "邮政：" +yzPrice+ "\n"
         + "百世：" + bsPrice+ "\n"
         + "圆通：" +ytPrice ;
        return str;
    }
    
}
