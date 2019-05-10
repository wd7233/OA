package rml.util;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//每个客户端连接成功的时候在后台都会创建一个相应的MyWebsocket类
@Controller
@RequestMapping(value = {"Websocket"})
@ServerEndpoint("/websocket")
public class WebSocket extends HttpServlet
{
    
/**
     * 
     */
    private static final long serialVersionUID = 1L;

//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocket> websocketPools = new CopyOnWriteArraySet<WebSocket>();
    
//与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    /**
     *      * 连接建立成功调用的方法
     *      * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     *      
     */
    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
        websocketPools.add(this);
    }
    
    @OnClose
    public void onClose()
    {
        websocketPools.remove(this);
    }
    
    @OnMessage
    public void onMessage(String message, Session session)
    {
        for (WebSocket item : websocketPools)
        {
            try
            {
                item.send(message);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void send(String message)
        throws IOException
    {
        this.session.getAsyncRemote().sendText(message);
    }
    
    @OnError
    public void onError(Session session, Throwable error)
    {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    
    /**
     * 
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * 
     * @throws IOException
     * 
     *             发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     * 
     */
    
    public void sendMessage()
        throws IOException
    {
        
        // 群发消息
        
        for (WebSocket item : websocketPools)
        {
            
            try
            {
                
                item.session.getBasicRemote().sendText("1");
                
            }
            catch (IOException e)
            {
                
                e.printStackTrace();
                
                continue;
                
            }
            
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        
        String msg;
        
        // 这里submit是数据库操作的方法，如果插入数据成功，则发送更新信号
        
        sendMessage();
        msg = "ok!";
        
        response.sendRedirect("manager.jsp?msg=" + msg);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse reponse)
        throws ServletException, IOException
    {
        
        doGet(request, reponse);
        
    }
    public static void main(String[] args) throws IOException
    {
        WebSocket ws = new WebSocket();
        ws.sendMessage();
    }
}
