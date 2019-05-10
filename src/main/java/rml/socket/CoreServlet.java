//package rml.socket;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.nio.CharBuffer;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import rml.model.CmzDanmu;
//import rml.pojo.ReceiveXmlEntity;
//import rml.util.FormatXmlProcess;
//import rml.util.ReceiveXmlProcess;
//import rml.util.SignUtil;
//
///** * @author wdj * 核心请求处理servlet * */
//public class CoreServlet extends HttpServlet
//{
//    private static final long serialVersionUID = 1L;
//    
//    private static final HashMap<Integer, String> color = new HashMap<Integer, String>()
//    {// 弹幕颜色
//        private static final long serialVersionUID = 1L;
//        {
//            put(1, "red");
//            put(2, "white");
//            put(3, "green");
//            put(4, "blue");
//            put(5, "yellow");
//        }
//    };
//    
//    public CoreServlet()
//    {
//        super();
//    }
//    
//    public void destroy()
//    {
//        super.destroy(); // Just puts "destroy" string in log
//        // Put your code here
//    }
//    
//    @RequestMapping("/init")
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//    {
//        try
//        {
//            if ("GET".equals(request.getMethod()))
//            {
//                // 微信加密签名
//                String signature = request.getParameter("signature");
//                // 时间戳
//                String timestamp = request.getParameter("timestamp");
//                // 随机数
//                String nonce = request.getParameter("nonce");
//                // 随机字符串
//                String echostr = request.getParameter("echostr");
//                
//                PrintWriter out = response.getWriter();
//                
//                // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//                if (SignUtil.checkSignature(signature, timestamp, nonce))
//                {
//                    out.print(echostr);
//                }
//                
//                out.close();
//                out = null;
//            }
//            else
//            {
//                
//                /** 读取接收到的xml消息 */
//                StringBuffer sb = new StringBuffer();
//                InputStream is = request.getInputStream();
//                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//                BufferedReader br = new BufferedReader(isr);
//                String s = "";
//                while ((s = br.readLine()) != null)
//                {
//                    sb.append(s);
//                }
//                String result = "";
//                /** 解析xml数据 */
//                ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(sb.toString());
//                System.out.println(sb.toString());
//                if ("event".endsWith(xmlEntity.getMsgType()) && "subscribe".endsWith(xmlEntity.getEvent()))
//                {
//                    return;
//                    
//                }
//                if ("text".endsWith(xmlEntity.getMsgType()))
//                {
//                    // result = JuheDemo.getRequest1(xmlEntity.getContent(), xmlEntity.getToUserName());
//                   // result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
//                    // 发送方帐号（open_id）
//                    String fromUserName = xmlEntity.getFromUserName();
//                    // 公众帐号
//                    String toUserName = xmlEntity.getToUserName();
//                    // 消息类型
//                    String msgType = xmlEntity.getMsgType();
//                    // 消息文本
//                    String content = xmlEntity.getContent();
//                    CmzDanmu cdm = new CmzDanmu();
//                    cdm.setContent(content);
//                    cdm.setIsPass(1);
//                    cdm.setOpenId(fromUserName);
//                    //插入 
//                    String danmuText = "{ \"text\":\"加油\",\"color\":\"white\",\"size\":\"1\",\"position\":\"0\",\"time\":";
//                    // 随机选取发送的弹幕的类型和颜色
//                    int ranNum = coreService.getRandomNum();
//                    int position = coreService.getPosition();
//                    if (ranNum < 10)
//                    {
//                        danmuText = "{ \"text\":\"" + message + "\",\"color\":\"" + color.get(ranNum / 5) + "\",\"size\":\"" + position + "\",\"position\":\"1\",\"time\":";
//                    }
//                    else if (ranNum < 20)
//                    {
//                        danmuText = "{ \"text\":\"" + message + "\",\"color\":\"" + color.get(ranNum / 5) + "\",\"size\":\"" + position + "\",\"position\":\"2\",\"time\":";
//                    }
//                    else
//                    {
//                        danmuText = "{ \"text\":\"" + message + "\",\"color\":\"" + color.get(ranNum / 5) + "\",\"size\":\"" + position + "\",\"position\":\"0\",\"time\":";
//                    }
//                    // 调用核心业务类接收消息、处理消息
//                    respMessage = coreService.processRequest(textMessage);
//                    broadcast(danmuText);// 将微信消息组装的弹幕格式的消息传入websocket通道
//                }
//                try
//                {
//                    OutputStream os = response.getOutputStream();
//                    os.write(result.getBytes("UTF-8"));
//                    os.flush();
//                    os.close();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//    }
//    
//    
//    public void init()
//        throws ServletException
//    {
//        // Put your code here
//    }
//    
//    @SuppressWarnings("deprecation")
//    private void broadcast(String message)
//    {// 将消息传入websocket通道中
//        ServletContext application = this.getServletContext();
//        @SuppressWarnings("unchecked")
//        Set<MyMessageInbound> connections = (Set<MyMessageInbound>)application.getAttribute("connections");
//        if (connections == null)
//        {
//            return;
//        }
//        
//        for (MyMessageInbound connection : connections)
//        {
//            try
//            {
//                CharBuffer buffer = CharBuffer.wrap(message);
//                connection.getWsOutbound().writeTextMessage(buffer);
//            }
//            catch (IOException ignore)
//            {
//                // Ignore
//            }
//        }
//    }
//    
//}