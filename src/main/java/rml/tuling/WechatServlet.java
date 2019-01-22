package rml.tuling;


import java.util.ArrayList;
import java.util.List;

/**
 * 微信服务端收发消息接口
 * 
 * @author pamchen-1
 * 
 */
public class WechatServlet  {

        public static void main(String[] args)
        {
            List<String > valueto = new ArrayList<String>();
            valueto.add("1");
            valueto.add("2");
            valueto.add("3");
            valueto.add("4");
            valueto.add("5");
            valueto.add("6");
            valueto.add("7");
            valueto.add("8");
            valueto.add("9");
            valueto.add("10");
            valueto.add("11");
            int i = 0;
            while(i<30) 
            {
                int random = (int) (Math.random() * valueto.size()); 
                System.out.println(valueto.get(random));
                i++;
            }
            
        }
}
