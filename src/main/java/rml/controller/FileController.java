package rml.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import rml.model.Express;
import rml.model.Goods;
import rml.model.Shop;
import rml.service.ExpressServiceI;
import rml.service.GoodServiceI;
import rml.service.ShopServiceI;

@Controller
@RequestMapping("/fileController")
public class FileController implements ServletContextAware
{
    
    @Autowired
    private GoodServiceI goodService;
    
    private ServletContext servletContext;
    
    @Autowired
    private ShopServiceI shopService;
    
    @Autowired
    private ExpressServiceI expressService;
    
    @Override
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }
    
    @RequestMapping(value = "/importShop")
    @ResponseBody
    public int importShop(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, Integer goodType)
        throws IOException
    {
        writeFile(file.getInputStream(), "./" + new Date().getTime() + ".csv");
        List<Shop> shopList = new ArrayList<Shop>();
        BufferedReader reader = null;
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                line++;
                if (line == 1)
                {
                    continue;
                }
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
        for (Shop shop : shopList)
        {
            Shop shopDb = shopService.selectByAccount(shop.getAccount());
            if (shopDb == null)
            {
                shopService.addShop(shop);
            }
            else
            {
                shopDb.setIsopen(shop.getIsopen());
                shopService.updateShop(shopDb);
            }
        }
        return shopList.size();
    }
    
    @RequestMapping(value = "/importTaobao")
    @ResponseBody
    public int importTaobao(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, Integer goodType)
        throws IOException
    {
        
        System.out.println("=============" + goodType);
        List<String> idList = null;
        if (goodType == 1 || goodType == 2)
        {
            idList = importPdd(file.getInputStream());
        }
        else
        {
            idList = importTb(file.getInputStream());
        }
        
        int cnt = 0;
        for (String str : idList)
        {
            System.out.println(str);
            Goods good = new Goods();
            good.setGoodsid(str);
            good.setCreatTime(new Date());
            good.setGoodType(goodType);
            if (goodService.seletcByGoodId(str) == 0)
            {
                cnt++;
                goodService.insert(good);
            }
        }
        return cnt;
    }
    
    @RequestMapping(value = "/importExpress")
    @ResponseBody
    public int importExpress(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, Integer expressType)
        throws IOException
    {
        List<Express> epList = new ArrayList<Express>();
        BufferedReader reader = null;
        try
        {
            System.out.println(expressType);
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
            String tempString = null;
            String[] weight = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                line++;
                if (line == 1)
                {
                    weight = tempString.split(",");
                    continue;
                }
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
                System.out.println(tempString);
                String[] arr = tempString.split(",");
                for (int i = 1; i < arr.length; i++)
                {
                    Express ep = new Express();
                    ep.setProvince(arr[0]+"省");
                    ep.setWeight(weight[i].substring(0, weight[i].length() - 2));
                    ep.setPrice(arr[i]);
                    ep.setState(0);
                    ep.setType(expressType);
                    epList.add(ep);
                }
                
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
        for (Express ep : epList)
        {
            Express expressDB = expressService.selectPrice(ep.getWeight(), ep.getProvince(),ep.getType());
            if (expressDB == null &&  !StringUtils.isEmpty(ep.getProvince()))
            {
                expressService.insert(ep);
            }
            else if(!StringUtils.isEmpty(ep.getProvince()))
            {
                ep.setId(expressDB.getId());
                expressService.updateByPrimaryKey(ep);
            }
        }
        return epList.size();
    }
    
    private List<String> importTb(InputStream is)
    {
        List<String> idList = new ArrayList<String>();
        BufferedReader reader = null;
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            
            reader = new BufferedReader(new InputStreamReader(is, "GBK"));
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null)
            {
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
                System.out.println(tempString);
                String[] strArr = tempString.split(",");
                line++;
                if (line == 1)
                {
                    continue;
                }
                if (StringUtils.isEmpty(strArr[0]))
                {
                    break;
                }
                System.out.println(strArr[0]);
                System.out.println(strArr[0].split("=")[1]);
                String id = strArr[0].split("=")[1].split("\\|")[0];
                System.out.println(id);
                idList.add(id.trim());
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
        return idList;
    }
    
    public List<String> importPdd(InputStream is)
    {
        List<String> idList = new ArrayList<String>();
        BufferedReader reader = null;
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(is, "GBK"));
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
                    idList.add(arr[1]);
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
        return idList;
    }
    
    @RequestMapping("file/download")
    public void fileDownload(HttpServletResponse response)
    {
        
        // 获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
        String path = servletContext.getRealPath("/");
        
        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        
        // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫zms.jpg,这里是设置名称)
        response.setHeader("Content-Disposition", "attachment;fileName=" + "goods.txt");
        
        ServletOutputStream out = null;
        FileInputStream inputStream = null;
        
        // 通过文件路径获得File对象(假如此路径中有一个 zms.jpg 文件)
        File file = new File(path + "download/" + "goods.txt");
        
        try
        {
            inputStream = new FileInputStream(file);
            
            // 3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();
            
            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1)
            {
                b = inputStream.read(buffer);
                if (b != -1)
                {
                    out.write(buffer, 0, b);// 4.写到输出流(out)中
                }
                
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (inputStream != null)
                {
                    inputStream.close();
                }
                if (out != null)
                {
                    out.close();
                    out.flush();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
        }
    }
    
    private void writeFile(InputStream is, String fileName)
    {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        in = new BufferedInputStream(is);
        try
        {
            out = new BufferedOutputStream(new FileOutputStream(fileName));
            int len = -1;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1)
            {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
