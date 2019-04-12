
// import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rml.model.OrderDetail;
import rml.service.OrderServiceI;
import rml.service.SpecialOrderServiceI;
import rml.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class OrderTest
{
    @Autowired
    private OrderServiceI orderService;
    
    @Autowired
    private SpecialOrderServiceI specialOrderService;
    
    @Test
    public void test1()
    {
        fileImport("./123.csv");
    }
    @Test
    public void test11()
    {
        Date end =  DateUtil.strToDateLong("2019-04-01", "yyyy-MM-dd");
        System.out.println(end);

    }
    private void fileImport(String fileName)
    {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<String> sts = new ArrayList<String>();
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                // 显示行号
                String[] arr = tempString.split(",");
                if (arr.length > 1)
                {
                    System.out.println(arr[0]);
                    int cnt = specialOrderService.deleteByOrderId(arr[0]);
                    if (cnt > 0)
                    {
                        line++;
                    }
                    else 
                    {
                        sts.add(arr[0]);
                    }
                }
                
            }
            reader.close();
            System.out.println(line);
            for(String s : sts) 
            {
                System.out.println(s);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e1)
                {
                }
            }
        }
        return;
    }
}
