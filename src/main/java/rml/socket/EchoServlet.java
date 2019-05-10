//package rml.socket;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.catalina.websocket.StreamInbound;
//import org.apache.catalina.websocket.WebSocketServlet;
//
//
//@SuppressWarnings("deprecation")
//
//// 处理WebSocket的Servlet需要继承自WebSocketServlet
//public class EchoServlet extends WebSocketServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected StreamInbound createWebSocketInbound(String subProtocol,
//            HttpServletRequest request) {
//        ServletContext application = this.getServletContext();
//        return new MyMessageInbound(application);
//    }
//
//}