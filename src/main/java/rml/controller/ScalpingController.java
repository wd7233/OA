package rml.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import rml.model.CardAmountInfo;
import rml.model.Paging;
import rml.model.ScalpingOrder;
import rml.model.SpPingjia;
import rml.model.SpecialOrder;
import rml.model.WithdrawName;
import rml.service.ScalpingServiceI;
import rml.service.WithdrawService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/scalpingController")
public class ScalpingController
{
    
    @Autowired
    private ScalpingServiceI scalpingService;
    
    @RequestMapping(value = "/getOrder")
    public String getScalpingOrder(HttpServletRequest request)
    {
        Paging p = new Paging();
//        int pageNumber =Integer.parseInt(request.getParameter(""));
        String startTime = request.getParameter("startDate");
        String endTime = request.getParameter("endDate");
        String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord").trim();
        Integer orderType = Integer.parseInt(request.getParameter("orderType") == null ? "-1" : request.getParameter("orderType"));
        int totalRecord;
//        if(StringUtils.isEmpty(request.getParameter("totalRecord"))) 
//        {
//            totalRecord = scalpingService.selectOrderCount(startTime, endTime, "5533035595", keyWord, orderType);
//        }
//        else 
//        {
//            totalRecord  = Integer.parseInt(request.getParameter(""));
//        }
        p.setTotalRecord(100);
        p.setPageNumber(1);
        p.setPageSize(10);
        List<ScalpingOrder> orderList = scalpingService.selectOrder(startTime, endTime, "5533035595", keyWord, orderType, p);
        request.setAttribute("orderList", orderList);
        Double totalPrice = new Double(0);
        int orderCnt = 0;
        int backCnt = 0;
        for (ScalpingOrder so : orderList)
        {
            if ("无售后或售后取消".equals(so.getAfterState()))
            {
                orderCnt++;
                
                BigDecimal b1 = new BigDecimal(Double.toString(totalPrice));
                Double price = so.getSkuPirce();
                BigDecimal b2 = new BigDecimal(Double.toString(price));
                totalPrice = b1.add(b2).doubleValue();
                if (price > 29)
                {
                    totalPrice = new BigDecimal(Double.toString(totalPrice)).add(new BigDecimal(Double.toString(new Double(3)))).doubleValue();
                }
                else
                {
                    totalPrice = new BigDecimal(Double.toString(totalPrice)).add(new BigDecimal(Double.toString(new Double(2.5)))).doubleValue();
                    
                }
            }
            else if ("订单未关闭".equals(so.getAfterState()))
            {
                orderCnt++;
                BigDecimal b1 = new BigDecimal(Double.toString(totalPrice));
                Double price = so.getSkuPirce();
                BigDecimal b2 = new BigDecimal(Double.toString(price));
                totalPrice = b1.add(b2).doubleValue();
                totalPrice = new BigDecimal(Double.toString(totalPrice)).add(new BigDecimal(Double.toString(new Double(8)))).doubleValue();
            }
            else
            {
                backCnt++;
            }
            
        }
        request.setAttribute("orderCnt", orderCnt);
        request.setAttribute("backCnt", backCnt);
        request.setAttribute("totalPrice", totalPrice);
        return "scalpingOrder";
    }
    
    @RequestMapping(value = "/getOrderS")
    public String getScalpingOrderS(HttpServletRequest request, String startDate, String endDate, String keyWord)
    {
        
        Integer orderType = Integer.parseInt(request.getParameter("orderType") == null ? "-1" : request.getParameter("orderType"));
        Paging p = new Paging();
//        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int totalRecord;
        if (StringUtils.isEmpty(request.getParameter("totalRecord")))
        {
            totalRecord = scalpingService.selectOrderCount("2019-06-01 00:00:00", "2019-07-11 00:00:00", null, keyWord, orderType);
        }
        else
        {
            totalRecord = Integer.parseInt(request.getParameter(""));
        }
        p.setTotalRecord(totalRecord);
        p.setPageNumber(1);
        p.setPageSize(15);
        List<ScalpingOrder> orderList = scalpingService.selectOrder("2019-06-01 00:00:00", "2019-07-11 00:00:00", null, keyWord, orderType, p);
        request.setAttribute("orderList", orderList);
        Double totalPrice = new Double(0);
        int orderCnt = 0;
        int backCnt = 0;
        
        for (ScalpingOrder so : orderList)
        {
            if ("无售后或售后取消".equals(so.getAfterState()))
            {
                orderCnt++;
                
                BigDecimal b1 = new BigDecimal(Double.toString(totalPrice));
                Double price = so.getSkuPirce();
                BigDecimal b2 = new BigDecimal(Double.toString(price));
                totalPrice = b1.add(b2).doubleValue();
                if (price > 29)
                {
                    totalPrice = new BigDecimal(Double.toString(totalPrice)).add(new BigDecimal(Double.toString(new Double(3)))).doubleValue();
                }
                else
                {
                    totalPrice = new BigDecimal(Double.toString(totalPrice)).add(new BigDecimal(Double.toString(new Double(2.5)))).doubleValue();
                    
                }
            }
            else if ("订单未关闭".equals(so.getAfterState()))
            {
                orderCnt++;
                BigDecimal b1 = new BigDecimal(Double.toString(totalPrice));
                Double price = so.getSkuPirce();
                BigDecimal b2 = new BigDecimal(Double.toString(price));
                totalPrice = b1.add(b2).doubleValue();
                totalPrice = new BigDecimal(Double.toString(totalPrice)).add(new BigDecimal(Double.toString(new Double(8)))).doubleValue();
            }
            else
            {
                backCnt++;
            }
            
        }
        request.setAttribute("totalRecord", totalRecord);
        request.setAttribute("orderCnt", orderCnt);
        request.setAttribute("backCnt", backCnt);
        request.setAttribute("totalPrice", totalPrice);
        return "scalpingOrder";
    }
    
    @Transactional
    @RequestMapping(value = "/importOrder")
    @ResponseBody
    public int importOrder(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        
        int cnt = readFile(file.getInputStream(), file.getOriginalFilename().substring(0, file.getOriginalFilename().length() - 4));
        return cnt;
    }
    
    @Transactional
    @RequestMapping(value = "/importPingjia")
    @ResponseBody
    public int importPingjia(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        
        int cnt = addPingjia(file.getInputStream());
        return cnt;
    }
    
    private int addPingjia(InputStream is)
    {
        BufferedReader reader = null;
        int line = 0;
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(is, "GBK"));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            boolean isOne = true;
            while ((tempString = reader.readLine()) != null)
            {
                System.out.println(tempString);
                String[] arr = tempString.split(",");
                if (arr.length == 2)
                {
                    String content = arr[0].trim();
                    String good = arr[1].trim();
                    SpPingjia sp = new SpPingjia();
                    sp.setContent(content);
                    sp.setGood(good);
                    scalpingService.insetrPingjia(sp);
                    line++;
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
        return line;
    }
    
    private int readFile(InputStream is, String fileName)
    {
        List<String> noOrderId = new ArrayList<String>();
        BufferedReader reader = null;
        int line = 0;
        List<String> sts = new ArrayList<String>();
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            
            reader = new BufferedReader(new InputStreamReader(is, "GBK"));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            boolean isOne = true;
            while ((tempString = reader.readLine()) != null)
            {
                System.out.println(tempString);
                String[] arr = tempString.split(",");
                if (arr.length == 4)
                {
                    String orderId = arr[0];
                    String consignee = arr[1];
                    String phone = arr[2];
                    double skuPirce = Double.parseDouble(arr[3]);
                    ScalpingOrder so = new ScalpingOrder();
                    so.setOrderId(orderId);
                    so.setConsignee(consignee);
                    so.setPhone(phone);
                    so.setSkuPirce(skuPirce);
                    so.setCreateTime(new Date());
                    so.setGoodNumber(fileName);
                    if (scalpingService.selectByOrderId(orderId) == null)
                    {
                        scalpingService.insert(so);
                        line++;
                    }
                    else
                    {
                        noOrderId.add(orderId);
                    }
                }
                else if (arr.length == 5)
                {
                    String orderId = arr[0];
                    String consignee = arr[1];
                    String phone = arr[2];
                    double skuPirce = Double.parseDouble(arr[4]);
                    ScalpingOrder so = new ScalpingOrder();
                    so.setOrderId(orderId);
                    so.setConsignee(consignee);
                    so.setPhone(phone);
                    so.setSkuPirce(skuPirce);
                    so.setCreateTime(new Date());
                    so.setGoodNumber(fileName);
                    if (scalpingService.selectByOrderId(orderId) == null)
                    {
                        scalpingService.insert(so);
                        line++;
                    }
                    else
                    {
                        noOrderId.add(orderId);
                    }
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
        if (noOrderId.size() > 0)
        {
            System.out.println("====" + noOrderId.size() + "====");
            for (String orderId : noOrderId)
            {
                System.out.println(orderId);
            }
        }
        return line;
    }
}
