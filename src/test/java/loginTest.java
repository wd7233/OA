import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import rml.model.Goods;
import rml.model.Shop;
import rml.service.GoodServiceI;
import rml.service.ShopServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class loginTest
{
    @Autowired
    private ShopServiceI shopService;
    
    @Autowired
    private GoodServiceI goodService;

    private Shop shopDb;
    
    @Test
    public void mainTest()
    {
        String str1 = "\"a21q321\"";
        System.out.println(str1.replace("\"", ""));
    }
    
    @Test
    public void fileTest()
    {
        // readFileByLines("./1111.csv");
        List<Shop> shopList = add("./1.csv");
        for (Shop shop : shopList)
        {
          Shop  shopDb = shopService.selectByAccount(shop.getAccount());
            if (shopDb ==null)
            {
                shopService.addShop(shop);
            }
            else 
            {
                shopDb.setIsopen(shop.getIsopen());
                shopService.updateShop(shopDb);
            }
        }
        // 导入商品
        // List<String> strList = addGoods("./1031.txt");
        
        // List<String> strList = getVtdId("./1234.txt");
        
        // 获取未接入代购的店铺信息
        // getNeedVtdShop();
        
        // writeFile( getTaobaoId("./yqsn.txt"),"./over.txt");
        // 删除重复数据
        // deleteGoods();
    }
    
    /**
     * 店铺导入
     */
    public List<Shop> add(String fileName)
    {
        List<Shop> shopList = new ArrayList<Shop>();
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
                // System.out.println("line " + line + ": " + tempString);
                System.out.println(tempString);
                String[] arr = tempString.split(",");
                Shop shop = new Shop();
                shop.setNumber(arr[1]);
                shop.setName(arr[2]);
                shop.setAccount(arr[3]);
                shop.setPassword(arr[4]);
                shop.setUserName(arr[5]);
                shop.setCategory(arr[6]);
                shop.setInCode(arr[7]);
                if ("1".equals(arr[8]))
                {
                    shop.setIsopen(0);
                }
                else
                {
                    shop.setIsopen(1);
                }
                shopList.add(shop);
                
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
        return shopList;
    }
    
    /**
     * 负责人更新
     */
    public List<Shop> update(String fileName)
    {
        List<Shop> shopList = new ArrayList<Shop>();
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
                // System.out.println("line " + line + ": " + tempString);
                String[] arr = tempString.split(",");
                Shop shop = shopService.getByNumber(arr[0].trim().replace("?", ""));
                shop.setStaffName(arr[1].trim());
                shopService.updateShop(shop);
                System.out.println(shop.toString());
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
        return shopList;
    }
    
    // 商品导入
    public List<String> addGoods(String fileName)
    {
        List<String> strList = new ArrayList<String>();
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
                // System.out.println("line " + line + ": " + tempString);
                String[] arr = tempString.split("=");
                if (arr.length > 1)
                {
                    Goods g = new Goods();
                    g.setGoodsid(arr[1]);
                    g.setCreatTime(new Date());
                    g.setStaffId(2);
                    int cnt = goodService.seletcByGoodId(g.getGoodsid());
                    if (cnt == 0 && arr[1].length() < 20)
                    {
                        goodService.insert(g);
                        System.out.println("插入成功" + line + "条");
                    }
                }
                else
                {
                    strList.add(tempString);
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
        return strList;
    }
    
    // 提取微风代购未授权的店铺ID
    public List<String> getVtdId(String fileName)
    {
        List<String> strList = new ArrayList<String>();
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
                // System.out.println("line " + line + ": " + tempString);
                String[] arr = tempString.split("=");
                if (arr.length > 1)
                {
                    System.out.println(line + "==" + arr[1]);
                    Shop s = shopService.getByNumber(arr[1]);
                    if (s != null)
                    {
                        s.setIsvtd(1);
                        shopService.updateShop(s);
                        strList.add(arr[1]);
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
        return strList;
    }
    
    //
    public List<String> getTaobaoId(String fileName)
    {
        List<String> strList = new ArrayList<String>();
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
                // System.out.println("line " + line + ": " + tempString);
                String[] arr = tempString.split("=");
                if (arr.length > 1)
                {
                    strList.add("id=" + arr[1]);
                    
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
        return strList;
    }
    
    private void getNeedVtdShop()
    {
        List<Shop> shopList = shopService.selectNeedVtd();
        StringBuffer sb = new StringBuffer("");
        for (Shop shop : shopList)
        {
            sb.append(shop.getNumber());
            sb.append("\r\n");
        }
        write("11-2.txt", sb.toString());
    }
    
    /**
     *
     * @param fileName 将要写入的文件名
     * @param text 写入的文本
     */
    public static void write(String fileName, String text)
    {
        try
        {
            PrintWriter p = new PrintWriter(new File(fileName));
            try
            {
                p.print(text);
            }
            finally
            {
                p.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    // 去重
    public int deleteGoods()
    {
        List<Goods> goods = goodService.selectAll();
        for (Goods g : goods)
        {
            int i = goodService.seletcByGoodId(g.getGoodsid());
            if (i > 1)
            {
                System.out.println(g.toString());
                goodService.deleteByPrimaryKey(g.getId());
            }
        }
        System.out.println(goods.size());
        return goods.size();
    }
    
    private void writeFile(List<String> strList, String fileName)
    {
        try
        {
            StringBuffer sb = new StringBuffer("");
            for (String str : strList)
            {
                sb.append(str + "\r\n");
            }
            File file = new File(fileName);
            
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
            
            System.out.println("Done");
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
