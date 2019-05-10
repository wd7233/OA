//package rml.socket;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.CharBuffer;
//import java.util.Set;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//import javax.servlet.ServletContext;
//
//import org.apache.catalina.websocket.MessageInbound;
//import org.apache.catalina.websocket.WsOutbound;
//
//@SuppressWarnings("deprecation")
//public class MyMessageInbound extends MessageInbound {
//    private ServletContext application;  
//    private Set<MyMessageInbound> connections = null;
//    @SuppressWarnings("unchecked")
//    public MyMessageInbound(ServletContext application) {  
//           this.application = application;  
//           connections =   
//               (Set<MyMessageInbound>)application.getAttribute("connections");  
//           if(connections == null) {  
//               connections =  
//                   new CopyOnWriteArraySet<MyMessageInbound>();  
//           }  
//       }  
//
//       @Override  
//       protected void onOpen(WsOutbound outbound) {  
//           connections.add(this);      
//           application.setAttribute("connections", connections);  
//       }  
//
//       @Override  
//       protected void onClose(int status) {  
//           connections.remove(this);  
//           application.setAttribute("connections", connections);  
//       }  
//
//       @Override  
//       protected void onBinaryMessage(ByteBuffer message) throws IOException {  
//           throw new UnsupportedOperationException(  
//                   "message not supported.");  
//       }  
//
//       @Override  
//       protected void onTextMessage(CharBuffer message) throws IOException {  
//           //System.out.println("hehehe");
//       }  
//
//}