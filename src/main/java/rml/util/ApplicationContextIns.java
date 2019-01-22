package rml.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextIns
{
    
    public static ApplicationContext ac;
    
    public ApplicationContextIns()
    {
        if (ac == null)
        {
            String[] config = {"spring/base-context.xml", "spring/service-context.xml"};
            ac = new ClassPathXmlApplicationContext(config);
        }
    }
    
}
