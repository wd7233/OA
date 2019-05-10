package rml.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rml.model.Express;
import rml.model.GoodWeight;
import rml.model.SpPingjia;
import rml.pojo.ReceiveXmlEntity;
import rml.service.CmzServiceI;
import rml.service.ExpressServiceI;
import rml.service.ScalpingServiceI;
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
public class DemoContorller<main>
{
    
    @Autowired
    private ExpressServiceI expressService;
    @Autowired
    private CmzServiceI cmzService;
    @Autowired
    private ScalpingServiceI scalpingService;
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
//                if ("event".endsWith(xmlEntity.getMsgType()) && "subscribe".endsWith(xmlEntity.getEvent()))
//                {
//                    return;
//                    
//                }
//                if ("text".endsWith(xmlEntity.getMsgType()))
//                {
//                    // result = JuheDemo.getRequest1(xmlEntity.getContent(), xmlEntity.getToUserName());
//                    result = this.selectExpress(xmlEntity.getContent());
//                    result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
//                    // 情侣链接
//                    // talkService.talkContent(xmlEntity);
//                }
//                if ("text".endsWith(xmlEntity.getMsgType()))
//                  {
//                      // result = JuheDemo.getRequest1(xmlEntity.getContent(), xmlEntity.getToUserName());
//                  //    result = this.selectExpress(xmlEntity.getContent());
//                      CmzDanmu cdm = new CmzDanmu();
//                      cdm.setCreateTime(new Date());
//                      cdm.setContent(xmlEntity.getContent());
//                      cdm.setOpenId(xmlEntity.getFromUserName());
//                      cdm.setIsPass(1);
//                      cmzService.insert(cdm);
//                      result = "感谢您的祝福哦~~我一定会茁壮成长滴(*^▽^*)";
//                      result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
//                      // 情侣链接
//                      // talkService.talkContent(xmlEntity);
//                  }
//                else 
//                {
//                     result = "亲爱的叔叔阿姨好~今天是我的一岁诞辰哦，快来给我送上你们的祝福吧(*╹▽╹*)";
//                     result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
//
//                }
                if ("text".endsWith(xmlEntity.getMsgType()))
                {
                  if(xmlEntity.getContent().split("，").length > 1 ) 
                  {
                      result = this.selectExpress(xmlEntity.getContent());
                  }
                  else 
                  {
                      SpPingjia sp = scalpingService.getSpPingjia(xmlEntity.getContent());
                      if(sp == null) 
                      {
                          result = JuheDemo.getRequest1(xmlEntity.getContent(), xmlEntity.getToUserName()); 
                      }
                      else 
                      { 
                          sp.setCount(sp.getCount()+1);
                          scalpingService.updateByPrimaryKey(sp);
                          result = sp.getContent().trim(); 
                      }
                  }
                result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);

                }
                // if("image".endsWith(xmlEntity.getMsgType()))
                // {
                // result = returnService.savePicture(xmlEntity);
                // }
//                if ("location".endsWith(xmlEntity.getMsgType()))
//                {
//                    // result = returnService.LocationReturn(xmlEntity);
//                    String x = xmlEntity.getLocation_X();
//                    String y = xmlEntity.getLocation_Y();
//                    System.out.println(x);
//                    System.out.println("==========================");
//                    System.out.println(y);
//                }
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
        String str = "";
        if (content.split("，").length < 3 && content.split("，")[1].length() <3)
        {
            String province = content.split("，")[0];
            String weight = content.split("，")[1];
            Express baishiExpress = expressService.selectPrice(weight, province, 0);
            Express youzhengExpress = expressService.selectPrice(weight, province, 1);
            // Express shengtongExpress = expressService.selectPrice(weight, province, 2);
            Express yuantongExpress = expressService.selectPrice(weight, province, 3);
            String yzPrice = youzhengExpress == null ? "-" : youzhengExpress.getPrice();
            String bsPrice = baishiExpress == null ? "-" : baishiExpress.getPrice();
            String ytPrice = yuantongExpress == null ? "-" : yuantongExpress.getPrice();
            str += "省份：" + province + ", 重量：" + weight + "Kg\n";
            str += "邮政：" + yzPrice + "\n" + "百世：" + bsPrice + "\n" + "圆通：" + ytPrice;
        }
        else
        {
            String province = content.split("，")[0];
            String sku = content.split("，")[1];
            int cnt = content.split("，").length == 3 ? Integer.parseInt(content.split("，")[2]) : 1;
            GoodWeight g  = expressService.selectWeightBySku(sku);
            String weightDB =   g == null?"0":  g.getWeight();
            Double weightDouble = Math.ceil(Double.parseDouble(weightDB) * cnt);
            String weight = weightDouble.intValue() + "";
            Express baishiExpress = expressService.selectPrice(weight, province, 0);
            Express youzhengExpress = expressService.selectPrice(weight, province, 1);
            // Express shengtongExpress = expressService.selectPrice(weight, province, 2);
            Express yuantongExpress = expressService.selectPrice(weight, province, 3);
            String yzPrice = youzhengExpress == null ? "-" : youzhengExpress.getPrice();
            String bsPrice = baishiExpress == null ? "-" : baishiExpress.getPrice();
            String ytPrice = yuantongExpress == null ? "-" : yuantongExpress.getPrice();
            str += "省份：" + province + ", 重量：" + weight + "Kg\n";
            str += "邮政：" + yzPrice + "\n" + "百世：" + bsPrice + "\n" + "圆通：" + ytPrice;
        
            
        }
        return str;
    }
    
}
