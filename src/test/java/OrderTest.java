
// import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rml.model.OrderDetail;
import rml.service.OrderServiceI;

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class OrderTest
{
    @Autowired
    private OrderServiceI orderService;
    @Test
    public void test1()
    {
        fileImport("./1119.csv");
    }
    
    private void fileImport(String fileName)
    {
        File file = new File(fileName);
        BufferedReader reader = null;
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                // 显示行号
                String[] arr = tempString.split(",");
                if (arr.length > 1)
                {
                    System.out.println(arr[0]);
                    OrderDetail orderDB = orderService.selectByOrderId(arr[0]);
                    if (orderDB != null)
                    {
                        // cj45306-233699406221569119#
                        // 启程A1-258166979696953028#
                        String tbId = arr[1];
                        System.out.println(tbId.substring(12, 15));
                        if ("569".equals(tbId.substring(12, 15)))
                        {
                            tbId = tbId.substring(0,15)+"119";
                        }
                        if("953".equals(tbId.substring(12, 15))) 
                        {
                            tbId = tbId.substring(0,15)+"028";
                        }
                        System.out.println(arr[0]+"===="+tbId);
                        orderDB.setTbId(tbId);
                       orderService.updateByPrimaryKey(orderDB);
                    }
                }
                
                line++;
            }
            reader.close();
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
