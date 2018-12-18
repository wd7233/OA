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

public class FileUtil
{
    //訂單导入
    public static List<OrderDetail> orderImportExcel(InputStream is )
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
                        if (hssfRow.getCell(1) == null || hssfRow.getCell(2) == null || hssfRow.getCell(3) == null || hssfRow.getCell(5) == null || hssfRow.getCell(9) == null|| hssfRow.getCell(10) == null ||hssfRow.getCell(12) == null ||hssfRow.getCell(8) == null)
                        {
                            System.out.println(" 必填项");
                            continue;
                        }
                        if (StringUtils.isEmpty(hssfRow.getCell(1).getStringCellValue()) || StringUtils.isEmpty(hssfRow.getCell(2).getStringCellValue())
                            || StringUtils.isEmpty(hssfRow.getCell(9).getStringCellValue()) || StringUtils.isEmpty(hssfRow.getCell(5).getStringCellValue())
                                || StringUtils.isEmpty(hssfRow.getCell(8).getStringCellValue()) || StringUtils.isEmpty(hssfRow.getCell(11).getStringCellValue())
                                    || StringUtils.isEmpty(hssfRow.getCell(10).getStringCellValue())|| StringUtils.isEmpty(hssfRow.getCell(12).getStringCellValue())
                            )
                        {
                            continue;
                        }
                        OrderDetail  order =  new OrderDetail();
                        //店铺编号
                        order.setShopNumber(hssfRow.getCell(1).getStringCellValue());
                        //订单编号
                        order.setOrderId(hssfRow.getCell(2).getStringCellValue());
                        //交易时间
                        order.setDealTime(DateUtil.strToDateLong(hssfRow.getCell(3).getStringCellValue(), "yyyy年MM月dd日HH时mm分ss秒"));
                       // 商品名称
                        order.setGoodName(hssfRow.getCell(4).getStringCellValue());
                        //商品详情-分类
                        order.setGoodDetail(hssfRow.getCell(5).getStringCellValue());
                        //商品数量
                        order.setCount(Integer.parseInt(hssfRow.getCell(6).getStringCellValue()));
                        //留言
                        order.setMessage(hssfRow.getCell(7).getStringCellValue());
                        //卖出价格
                        order.setSellPrice(hssfRow.getCell(8).getNumericCellValue());
                        //佣金
                        order.setCommission(hssfRow.getCell(9).getNumericCellValue());
                        //买入价格
                        order.setBuyPrice(hssfRow.getCell(10).getNumericCellValue());
                        //备注
                        order.setRemake(hssfRow.getCell(11).getStringCellValue());
                        //类型
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
    public static List<OrderDetail> orderImport(InputStream is )
    {
        List<OrderDetail> orderList = new ArrayList<OrderDetail>();
        BufferedReader reader = null;
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            
            reader = new BufferedReader(new InputStreamReader(is,"GBK"));
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
                if(line ==1)
                {
                    continue;
                }
                if(StringUtils.isEmpty(strArr[0]))
                {
                    break;
                }
                OrderDetail  order =  new OrderDetail();
                //店铺编号
                order.setShopNumber(strArr[1]);
                //订单编号
                order.setOrderId(strArr[2]);
                //交易时间
                order.setDealTime(DateUtil.strToDateLong(strArr[3], "yyyy年MM月dd日HH时mm分"));
               // 商品名称
                order.setGoodName(convertString(strArr[4]));
                //商品详情-分类
                order.setGoodDetail(convertString(strArr[5]));
                //商品数量
                order.setCount(Integer.parseInt(strArr[6]));
                //留言
                order.setMessage(strArr[7]);
                //卖出价格
                if(StringUtils.isEmpty(strArr[8].trim()))
                {
                    order.setSellPrice(new Double(0));
                }
                else
                {
                    order.setSellPrice(Double.parseDouble(strArr[8].trim()));
                }
                
                //淘宝-订单号 -9
                String tbId = strArr[9].trim();
                if(!StringUtils.isEmpty(tbId))
                {
                    if ("569".equals(tbId.substring(12, 15)))
                    {
                        tbId = tbId.substring(0,15)+"119";
                    }
                    if("953".equals(tbId.substring(12, 15))) 
                    {
                        tbId = tbId.substring(0,15)+"028";
                    }
                    order.setTbId(tbId);
                    order.setType(1);
                    //交易时间 -10
                    order.setCreateTime(DateUtil.strToDateLong(strArr[10], "yyyy年MM月dd日HH时mm分"));
                }
                else 
                {
                    order.setType(4);
                    order.setCreateTime(new Date());

                }
                order.setCommission(new Double(0));
                //佣金
//                if(StringUtils.isEmpty(strArr[11].trim()))
//                {
//                    order.setCommission(new Double(0));
//                }
//                else
//                {
//                    order.setCommission(Double.parseDouble(strArr[11].trim()));
//                }
                //买入价格
                if(StringUtils.isEmpty(strArr[11].trim()))
                {
                    order.setBuyPrice(new Double(0));
                }
                else
                {
                    order.setBuyPrice(Double.parseDouble(strArr[11].trim()));
                }
                //备注
                order.setRemake(strArr[12]);
                //类型
                if(!(strArr.length==13 ||StringUtils.isEmpty(strArr[13].trim())))
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
    private static String  convertString(String str)
    {
        return str.replaceAll("\"", "@");
            
    }
}
