package rml.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rml.model.OrderDetail;
import rml.model.SpecialOrder;
import rml.service.ShopServiceI;

public class FileUtil
{
    // 訂單导入
    public static List<OrderDetail> orderImportExcel(InputStream is)
    {
        /**
         * 针对Book类进行导入的操作
         * 
         * @return
         */
        List<OrderDetail> orderList = new ArrayList<OrderDetail>();
        try
        {
            Workbook hssfWorkbook = null;
            try
            {
                hssfWorkbook = new HSSFWorkbook(is);
            }
            catch (Exception ex)
            {
                // 解决read error异常
                hssfWorkbook = new XSSFWorkbook(is);
            }
            // 获取选项卡对象
            // HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++)
            {
                // HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null)
                {
                    continue;
                }
                // 循环取出每行的值
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
                {
                    try
                    {
                        Row hssfRow = hssfSheet.getRow(rowNum);
                        // 必填项
                        if (hssfRow.getCell(1) == null || hssfRow.getCell(2) == null || hssfRow.getCell(3) == null || hssfRow.getCell(5) == null || hssfRow.getCell(9) == null
                            || hssfRow.getCell(10) == null || hssfRow.getCell(12) == null || hssfRow.getCell(8) == null)
                        {
                            System.out.println(" 必填项");
                            continue;
                        }
                        if (StringUtils.isEmpty(hssfRow.getCell(1).getStringCellValue()) || StringUtils.isEmpty(hssfRow.getCell(2).getStringCellValue())
                            || StringUtils.isEmpty(hssfRow.getCell(9).getStringCellValue()) || StringUtils.isEmpty(hssfRow.getCell(5).getStringCellValue())
                            || StringUtils.isEmpty(hssfRow.getCell(8).getStringCellValue()) || StringUtils.isEmpty(hssfRow.getCell(11).getStringCellValue())
                            || StringUtils.isEmpty(hssfRow.getCell(10).getStringCellValue()) || StringUtils.isEmpty(hssfRow.getCell(12).getStringCellValue()))
                        {
                            continue;
                        }
                        OrderDetail order = new OrderDetail();
                        // 店铺编号
                        order.setShopNumber(hssfRow.getCell(1).getStringCellValue());
                        // 订单编号
                        order.setOrderId(hssfRow.getCell(2).getStringCellValue());
                        // 交易时间
                        order.setDealTime(DateUtil.strToDateLong(hssfRow.getCell(3).getStringCellValue(), "yyyy年MM月dd日HH时mm分ss秒"));
                        // 商品名称
                        order.setGoodName(hssfRow.getCell(4).getStringCellValue());
                        // 商品详情-分类
                        order.setGoodDetail(hssfRow.getCell(5).getStringCellValue());
                        // 商品数量
                        order.setCount(Integer.parseInt(hssfRow.getCell(6).getStringCellValue()));
                        // 留言
                        order.setMessage(hssfRow.getCell(7).getStringCellValue());
                        // 卖出价格
                        order.setSellPrice(hssfRow.getCell(8).getNumericCellValue());
                        // 佣金
                        order.setCommission(hssfRow.getCell(9).getNumericCellValue());
                        // 买入价格
                        order.setBuyPrice(hssfRow.getCell(10).getNumericCellValue());
                        // 备注
                        order.setRemake(hssfRow.getCell(11).getStringCellValue());
                        // 类型
                        order.setType(Integer.parseInt(hssfRow.getCell(12).getStringCellValue()));
                        order.setCreateTime(new Date());
                        orderList.add(order);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return orderList;
    }
    
    public static List<OrderDetail> orderImport(InputStream is)
    {
        List<OrderDetail> orderList = new ArrayList<OrderDetail>();
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
                OrderDetail order = new OrderDetail();
                // 店铺编号
                order.setShopNumber(strArr[1]);
                // 订单编号
                order.setOrderId(strArr[2]);
                // 交易时间
                order.setDealTime(DateUtil.strToDateLong(strArr[3], "yyyy年MM月dd日HH时mm分"));
                // 商品名称
                order.setGoodName(convertString(strArr[4]));
                // 商品详情-分类
                order.setGoodDetail(convertString(strArr[5]));
                // 商品数量
                order.setCount(Integer.parseInt(strArr[6]));
                // 留言
                order.setMessage(strArr[7]);
                // 卖出价格
                if (StringUtils.isEmpty(strArr[8].trim()))
                {
                    order.setSellPrice(new Double(0));
                }
                else
                {
                    order.setSellPrice(Double.parseDouble(strArr[8].trim()));
                }
                
                // 淘宝-订单号 -9
                String tbId = strArr[9].trim();
                if (!StringUtils.isEmpty(tbId))
                {
                    if ("569".equals(tbId.substring(12, 15)))
                    {
                        tbId = tbId.substring(0, 15) + "119";
                    }
                    if ("953".equals(tbId.substring(12, 15)))
                    {
                        tbId = tbId.substring(0, 15) + "028";
                    }
                    order.setTbId(tbId);
                    order.setType(1);
                    // 交易时间 -10
                    order.setCreateTime(DateUtil.strToDateLong(strArr[10], "yyyy年MM月dd日HH时mm分"));
                }
                else
                {
                    order.setType(4);
                    order.setCreateTime(new Date());
                    
                }
                order.setCommission(new Double(0));
                // 佣金
                // if(StringUtils.isEmpty(strArr[11].trim()))
                // {
                // order.setCommission(new Double(0));
                // }
                // else
                // {
                // order.setCommission(Double.parseDouble(strArr[11].trim()));
                // }
                // 买入价格
                if (StringUtils.isEmpty(strArr[11].trim()))
                {
                    order.setBuyPrice(new Double(0));
                }
                else
                {
                    order.setBuyPrice(Double.parseDouble(strArr[11].trim()));
                }
                // 备注
                order.setRemake(strArr[12]);
                // 类型
                if (!(strArr.length == 13 || StringUtils.isEmpty(strArr[13].trim())))
                {
                    order.setType(Integer.parseInt(strArr[13].trim()));
                }
                orderList.add(order);
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
        return orderList;
    }
    
    public static List<SpecialOrder> specialOrderImport(InputStream is)
    {
        List<SpecialOrder> orderList = new ArrayList<SpecialOrder>();
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
                SpecialOrder order = new SpecialOrder();
                line++;
                if (line == 1)
                {
                    continue;
                }
                if (StringUtils.isEmpty(strArr[0]))
                {
                    break;
                }
               
                if (strArr.length < 50)
                {
                    
                    // 订单编号 1
                    order.setOrderId(strArr[1]);
                    // 订单状态 2
                    order.setState(strArr[2].trim());
                    // 实收金额 10
                    order.setPrice(Double.parseDouble(convertStringDouble(strArr[10]).trim()));
                    // 数量 11
                    order.setCount(Integer.parseInt(convertStringDouble(strArr[11]).trim()));
                    // 收货人 14
                    order.setConsignee(strArr[14]);
                    // 手机 15
                    order.setTelephone(convertStringDouble(strArr[15].trim()));
                    // 省 17
                    order.setProvince(strArr[17]);
                    // 市 18
                    order.setCity(strArr[18]);
                    // 区 19
                    order.setArea(strArr[19]);
                    // 街道 20
                    order.setStreet(convertString(strArr[20]));
                    // 订单确认时间 22
                    order.setCreateTime(DateUtil.strToDateLong(convertStringDouble(strArr[22]).trim(), "yyyy/MM/dd HH:mm"));
                    // 承诺发货时间 23
                    order.setPromiseSendTime(DateUtil.strToDateLong(convertStringDouble(strArr[23]).trim(), "yyyy/MM/dd HH:mm"));
                    // 发货时间24
                    order.setDeliverTime(DateUtil.strToDateLong(convertStringDouble(strArr[24]).trim(), "yyyy/MM/dd HH:mm"));
                    // 商品id 26
                    order.setGoodNumber(convertStringDouble(strArr[26]).trim());
                    // 商品规格 27
                    if (!StringUtils.isEmpty(convertStringDouble(strArr[27]).trim()))
                    {
                        if (strArr[27].split("@@").length > 1)
                        {
                            order.setColor(strArr[27].split("@@")[0]);
                            order.setSku(strArr[27].split("@@")[1]);
                        }
                        else
                        {
                            order.setSku(strArr[27].split("@@")[0]);
                        }
                    }
                    // 快递单号 31
                    order.setCourierNumber(convertStringDouble(strArr[31]));
                    // 快递公司 32
                    order.setCourierCompany(convertStringDouble(strArr[32]));
                    // 商家备注 37
                    order.setRemakes(convertStringDouble(strArr[37]));
                    // 售后状态 38
                    order.setAfterState(convertStringDouble(strArr[38].trim()));
                    // 买家留言 39
                    order.setMessage(convertStringDouble(strArr[39]));
                    orderList.add(order);
                }
                //淘宝
                else 
                {
                    // 订单编号 1
                    order.setOrderId(strArr[0]);
                    // 订单状态 2
                    order.setState(strArr[12].trim());
                    // 实收金额 10
                    order.setPrice(Double.parseDouble(convertStringDouble(strArr[8]).trim()));
                    // 数量 11
                    order.setCount(Integer.parseInt(convertStringDouble(strArr[26]).trim()));
                    // 收货人 14
                    order.setConsignee(strArr[14]);
                    // 手机 15
                    order.setTelephone(convertStringDouble(strArr[18].trim()));
//                    // 省 17
//                    order.setProvince(strArr[17]);
//                    // 市 18
//                    order.setCity(strArr[18]);
//                    // 区 19
//                    order.setArea(strArr[19]);
                    // 街道 20
                    order.setStreet(convertString(strArr[15]));
                    // 订单确认时间 22
                    order.setCreateTime(DateUtil.strToDateLong(convertStringDouble(strArr[20]).trim(), "yyyy/MM/dd HH:mm"));
                    // 确认收货时间 23
                    order.setPromiseSendTime(DateUtil.strToDateLong(convertStringDouble(strArr[53]).trim(), "yyyy/MM/dd HH:mm"));
//                    // 发货时间24
//                    order.setDeliverTime(DateUtil.strToDateLong(convertStringDouble(strArr[24]).trim(), "yyyy/MM/dd HH:mm"));
                    // 商品id 26
                    order.setGoodNumber("tb");
                    // 快递单号 31 
                    order.setCourierNumber(convertStringDouble(strArr[23]));
                    // 快递公司 32
                    order.setCourierCompany(convertStringDouble(strArr[24]));
                    // 商家备注 37
                    order.setRemakes(convertStringDouble(strArr[25]));
                    // 退款原因 38
                    order.setAfterState(convertStringDouble(strArr[29].trim()));
                    // 买家留言 39
                    order.setMessage(convertStringDouble(strArr[13]));
                    orderList.add(order);
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
        return orderList;
    }
    
    private static String convertString(String str)
    {
        return str.replaceAll("\"", "@");
    }
    
    private static String convertStringDouble(String str)
    {
        return str.replaceAll("\"", "");
    }
}
