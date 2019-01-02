package rml.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import rml.model.EditPrice;
import rml.model.OrderCommission;
import rml.model.OrderDetail;
import rml.model.OrderPddId;
import rml.model.OrderType;
import rml.model.Shop;
import rml.model.Staff;
import rml.service.CommissionServiceI;
import rml.service.EditPriceServiceI;
import rml.service.OrderPddServiceI;
import rml.service.OrderServiceI;
import rml.service.OrderTypeServiceI;
import rml.service.ShopServiceI;
import rml.service.StaffServiceI;
import rml.util.DateUtil;
import rml.util.FileUtil;

@Controller
@RequestMapping("/orderController")
public class OrderController
{
    
    @Autowired
    private StaffServiceI staffService;
    
    @Autowired
    private OrderServiceI orderService;
    
    @Autowired
    private EditPriceServiceI editPriceService;
    
    @Autowired
    private ShopServiceI shopService;
    
    @Autowired
    private OrderTypeServiceI orderTypeService;
    
    @Autowired
    private CommissionServiceI commissionService;
    
    @Autowired
    private OrderPddServiceI orderPddService;
    
    @Transactional
    @RequestMapping(value = "/importOrder")
    @ResponseBody
    public int importOrder(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        List<OrderDetail> orderList = FileUtil.orderImport(file.getInputStream());
        int cnt = 0;
        for (OrderDetail order : orderList)
        {
            if (order.getSellPrice() - order.getBuyPrice() < 5)
            {
                EditPrice ep = editPriceService.selectByOrderId(order.getOrderId());
                if (ep == null)
                {
                    ep = new EditPrice();
                    ep.setCreateTime(new Date());
                    ep.setPrice(order.getBuyPrice()+5);
                    ep.setOrderId(order.getOrderId());
                    ep.setState(0);
                    ep.setType(0);
                    editPriceService.insert(ep);
                }
            }
            
            OrderDetail orderDB = orderService.selectByOrderId(order.getOrderId());
            if (orderDB == null)
            {
                cnt++;
                orderService.insert(order);
                // 拼多多购买
                OrderPddId opi = orderPddService.selectByOrderId(order.getOrderId());
                if (opi != null)
                {
                    order.setBuyPrice(opi.getPddPrice());
                    order.setTbId(opi.getPddId());
                    order.setType(2);
                    orderService.updateByPrimaryKey(order);
                    if (order.getSellPrice() - order.getBuyPrice() < 5)
                    {
                        EditPrice ep = editPriceService.selectByOrderId(order.getOrderId());
                        if (ep == null)
                        {
                            ep = new EditPrice();
                            ep.setCreateTime(new Date());
                            ep.setPrice(order.getBuyPrice()+5);
                            ep.setOrderId(order.getOrderId());
                            ep.setState(0);
                            ep.setType(0);
                            editPriceService.insert(ep);
                        }
                    }
                    
                }
                
            }
            else
            {
                // 表格导入不进行更新
                // cnt++;
                // order.setId(orderDB.getId());
                // orderService.updateByPrimaryKey(order);
            }
        }
        System.out.println(orderList.size());
        return cnt;
    }
    
    // 订单查询-买手
    @RequestMapping(value = "/getOrderList")
    public String getOrderList(HttpServletRequest request)
    {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord");
        Integer staffId = Integer.parseInt(request.getParameter("staffIdSelect"));
        Integer orderType = Integer.parseInt(request.getParameter("orderType"));
        if (!StringUtils.isEmpty(keyWord))
        {
            startDate = "2018-01-01";
            endDate = "2021-12-31";
        }
        HttpSession session = request.getSession();
        Staff s = (Staff)session.getAttribute("user");
        List<Staff>   staffList = staffService.selectByRole(s.getId(), s.getRole(),s.getCompanyId());
        request.setAttribute("staffList", staffList);
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        List<OrderDetail> orderList = orderService.selectOrderList(start, end, keyWord.trim(),staffId,orderType);
        List<OrderType> orderTypeList = orderTypeService.selectAll();
        request.setAttribute("orderList", orderList);
        request.setAttribute("orderTypeList", orderTypeList);
        return "orderList";
    }
    
    // 查询需要涨价的订单
    @RequestMapping(value = "/getEditPriceOrderList")
    public String getEditPriceOrderList(HttpServletRequest request)
    {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord");
        Integer staffId = Integer.parseInt(request.getParameter("staffIdSelect"));
        if (!StringUtils.isEmpty(keyWord))
        {
            startDate = "2018-01-01";
            endDate = "2021-12-31";
        }
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        List<OrderDetail> orderList = orderService.selectOrderList(start, end, keyWord.trim(),-1,0);
        List<OrderType> orderTypeList = orderTypeService.selectAll();
        request.setAttribute("orderList", orderList);
        request.setAttribute("orderTypeList", orderTypeList);
        return "orderList";
    }
    
    // 新增订单
    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public String addOrder(String orderId, String tbId, Integer orderType, Double buyPrice, Double sellPrice, Double commission, String shopNumber, String goodName, String goodDetail, String message,
        String remake, Integer cnt)
    {
        OrderDetail orderDetail = orderService.selectByOrderId(orderId.trim());
        if (orderDetail != null)
        {
            orderDetail.setTbId(tbId.trim());
            orderDetail.setShopNumber(shopNumber.trim());
            orderDetail.setGoodDetail(goodDetail.trim());
            orderDetail.setGoodName(goodName.trim());
            orderDetail.setRemake(remake.trim());
            orderDetail.setCount(cnt);
            orderDetail.setMessage(message.trim());
            orderDetail.setSellPrice(sellPrice);
            orderDetail.setBuyPrice(buyPrice);
            orderDetail.setCommission(commission);
            orderDetail.setType(orderType);
            orderService.updateByPrimaryKey(orderDetail);
        }
        else
        {
            orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId.trim());
            orderDetail.setTbId(tbId.trim());
            orderDetail.setShopNumber(shopNumber.trim());
            orderDetail.setGoodDetail(goodDetail.trim());
            orderDetail.setGoodName(goodName.trim());
            orderDetail.setRemake(remake.trim());
            orderDetail.setMessage(message.trim());
            orderDetail.setSellPrice(sellPrice);
            orderDetail.setBuyPrice(buyPrice);
            orderDetail.setCommission(commission);
            orderDetail.setType(orderType);
            orderDetail.setDealTime(new Date());
            orderDetail.setCreateTime(new Date());
            orderDetail.setCount(cnt);
            orderService.insert(orderDetail);
        }
        OrderCommission oc = commissionService.selectByOrderId(orderId.trim());
        if (oc == null)
        {
            oc = new OrderCommission();
            oc.setCommission(commission);
            oc.setCreateTime(new Date());
            oc.setOrderId(orderId);
            commissionService.insert(oc);
        }
        else
        {
            oc.setCommission(commission);
            oc.setCreateTime(new Date());
            commissionService.updateByPrimaryKey(oc);
        }
        return "SUCCESS";
    }
    
    // 编辑订单
    @RequestMapping(value = "/editOrder")
    @ResponseBody
    public JSONObject updateNums(String orderId, Integer orderType, Double buyPrice, Double sellPrice, Double commission, String tbId, String remake)
    {
        OrderDetail orderDetail = orderService.selectByOrderId(orderId.trim());
        sellPrice = sellPrice == null ? Double.valueOf(0) : sellPrice;
        buyPrice = buyPrice == null ? Double.valueOf(0) : buyPrice;
        commission = commission == null ? Double.valueOf(0) : commission;
        orderDetail.setSellPrice(sellPrice);
        orderDetail.setBuyPrice(buyPrice);
        orderDetail.setCommission(commission);
        orderDetail.setType(orderType);
        orderDetail.setTbId(tbId);
        orderDetail.setRemake(remake);
        orderService.updateByPrimaryKey(orderDetail);
        OrderCommission oc = commissionService.selectByOrderId(orderId.trim());
        if (oc == null)
        {
            oc = new OrderCommission();
            oc.setCommission(commission);
            oc.setCreateTime(new Date());
            oc.setOrderId(orderId.trim());
            commissionService.insert(oc);
        }
        oc.setCommission(commission);
        commissionService.updateByPrimaryKey(oc);
        JSONObject json = new JSONObject();
        json.put("sellPrice", sellPrice);
        json.put("tbId", tbId);
        json.put("remake", remake);
        json.put("buyPrice", buyPrice);
        json.put("commission", commission);
        json.put("orderType", orderType);
        return json;
    }
    
    // 商品涨价
    @RequestMapping(value = "/editPrice")
    @ResponseBody
    public String editPrice(String orderId, Double price,Integer type)
    {
        EditPrice ep = editPriceService.selectByOrderId(orderId.trim());
        if (ep == null)
        {
            ep = new EditPrice();
            ep.setCreateTime(new Date());
            ep.setOrderId(orderId.trim());
            ep.setPrice(price);
            ep.setState(0);
            ep.setType(type);
            editPriceService.insert(ep);
        }
        else
        {
            ep.setPrice(price);
            ep.setType(type);
            editPriceService.updateByPrimaryKey(ep);
        }
        
        return "SUCCESS";
    }
    
    // 新增佣金
    @RequestMapping(value = "/addCommission")
    @ResponseBody
    public String addCommission(String orderId, Double commission)
    {
        OrderCommission oc = commissionService.selectByOrderId(orderId);
        if (oc == null)
        {
            oc = new OrderCommission();
            oc.setCreateTime(new Date());
            oc.setOrderId(orderId.trim());
            oc.setCommission(commission);
            commissionService.insert(oc);
        }
        else
        {
            oc.setCommission(commission);
            commissionService.updateByPrimaryKey(oc);
        }
        
        return "SUCCESS";
    }
    
    // 拼多多订单录入
    @RequestMapping(value = "/addPddOrder")
    @ResponseBody
    public String addPddOrder(String orderId, String buyId, Double price)
    {
        OrderPddId op = orderPddService.selectByOrderId(orderId);
        
        if (op == null)
        {
            op = new OrderPddId();
            op.setCreateTime(new Date());
            op.setOrderId(orderId.trim());
            op.setPddPrice(price);
            op.setPddId(buyId);
            orderPddService.insert(op);
        }
        else
        {
            op.setPddPrice(price);
            op.setPddId(buyId);
            orderPddService.updateByPrimaryKey(op);
        }
        
        return "SUCCESS";
    }
    
    // 商品涨价确认
    @RequestMapping(value = "/updateEditPriceState")
    @ResponseBody
    public String updateEditPriceState(String orderId)
    {
        EditPrice ep = editPriceService.selectByOrderId(orderId.trim());
        ep.setState(1);
        editPriceService.updateByPrimaryKey(ep);
        return "SUCCESS";
    }
    
    // // 订单查询-个人
    // @RequestMapping(value = "/getOrderListByUser")
    // public String getOrderListByUser(HttpServletRequest request)
    // {
    // List<OrderDetail> order = new ArrayList<OrderDetail>();
    // HttpSession session = request.getSession();
    // Staff staff = (Staff)session.getAttribute("user");
    // List<Staff> staffList = staffService.selectByRole(staff.getId(), staff.getRole());
    // String startDate = request.getParameter("startDate");
    // String endDate = request.getParameter("endDate");
    // Integer orderType = Integer.parseInt(request.getParameter("orderType"));
    // Integer staffId = request.getParameter("staffName") == null ? staff.getId() :
    // Integer.parseInt(request.getParameter("staffName"));
    // String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord");
    // if (!StringUtils.isEmpty(keyWord))
    // {
    // startDate = "2018-01-01";
    // endDate = "2021-12-31";
    // }
    // Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate,
    // "yy-MM-dd");
    // Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate,
    // "yy-MM-dd");
    // List<Shop> shopList = shopService.selectByUser(staffId);
    // for (Shop shop : shopList)
    // {
    // List<OrderDetail> orderList = orderService.selectOrderByUser(start, end, shop.getNumber(), keyWord.trim());
    // for (OrderDetail od : orderList)
    // {
    // od.setShopName(shop.getName());
    // order.add(od);
    // }
    // }
    // request.setAttribute("orderList", order);
    // request.setAttribute("staffList", staffList);
    // return "order";
    // }
    //
    // 订单查询-个人
    @RequestMapping(value = "/getOrderListByUser")
    public String getOrderListByUser(HttpServletRequest request)
    {
        //订单查询
        List<OrderDetail> order = new ArrayList<OrderDetail>();
        HttpSession session = request.getSession();
        Staff staff = (Staff)session.getAttribute("user");
        List<Staff> staffList = staffService.selectByRole(staff.getId(), staff.getRole(),staff.getCompanyId());
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Integer orderType = request.getParameter("orderType") == null ? 0 : Integer.parseInt(request.getParameter("orderType"));
        Integer staffId = request.getParameter("staffId") == null ? staff.getId() : Integer.parseInt(request.getParameter("staffId"));
        String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord");
        if (!StringUtils.isEmpty(keyWord))
        {
            startDate = "2018-01-01";
            endDate = "2021-12-31";
        }
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        List<OrderDetail> orderList = orderService.selectOrderListByUser(start, end, staffId, keyWord.trim(), orderType);
        request.setAttribute("orderList", orderList);
        request.setAttribute("staffList", staffList);
        //订单统计  总订单数，待解决订单，退款单数，退款率，待涨价订单，昨日日订单数
        Integer totalCnt = orderService.selectCountByUser(DateUtil.getMonthFirstDay(),DateUtil.getMonthEndDay(), staffId, 0);
        Integer proCnt = orderService.selectCountByUser(DateUtil.getMonthFirstDay(),DateUtil.getMonthEndDay(), staffId, 3);
        Integer backCnt = orderService.selectCountByUser(DateUtil.getMonthFirstDay(),DateUtil.getMonthEndDay(), staffId, 4);
        Integer yesterDayCnt = orderService.selectCountByUser(DateUtil.getYesterdayStart(), DateUtil.getYesterdayEnd(), staffId, 0);
        Integer editCnt = orderService.selectEditCount(DateUtil.getMonthFirstDay(),DateUtil.getMonthEndDay(), staffId);
        request.setAttribute("totalCnt", totalCnt);
        request.setAttribute("proCnt", proCnt);
        request.setAttribute("backCnt", backCnt);
        request.setAttribute("totalCnt", totalCnt);
        request.setAttribute("editCnt", editCnt);
        DecimalFormat df1 = new DecimalFormat("0.00%"); 
        double backCnt_double=backCnt*1.0;  
        request.setAttribute("backRate", df1.format(backCnt_double/totalCnt));
        request.setAttribute("yesterDayCnt", yesterDayCnt);
        return "order";
    }
    
    // 利润计算
    @RequestMapping(value = "/getLucre")
    @ResponseBody
    public JSONObject getLucre()
    {
        System.out.println("===========");
        JSONObject json = new JSONObject();
        List<OrderDetail> orderList = orderService.selectOrderForLucre(DateUtil.getStartTime(), DateUtil.getEndTime(), null);
        Double sell = new Double(0);
        Double buy = new Double(0);
        Double commission = new Double(0);
        System.out.println(orderList.size());
        for (OrderDetail od : orderList)
        {
            BigDecimal ddSell1 = new BigDecimal(String.valueOf(sell));
            BigDecimal ddSell2 = new BigDecimal(String.valueOf(od.getSellPrice()));
            sell = ddSell1.add(ddSell2).doubleValue();
            BigDecimal ddBuy1 = new BigDecimal(String.valueOf(buy));
            BigDecimal ddBuy2 = new BigDecimal(String.valueOf(od.getBuyPrice()));
            buy = ddBuy1.add(ddBuy2).doubleValue();
            BigDecimal ddCommission1 = new BigDecimal(String.valueOf(commission));
            BigDecimal ddCommission2 = new BigDecimal(String.valueOf(od.getCommission()));
            commission = ddCommission1.add(ddCommission2).doubleValue();
        }
        System.out.println(sell);
        System.out.println(buy);
        System.out.println(commission);
        // Double lucre = new BigDecimal(sell).add(new
        // BigDecimal(commission)).subtract(new
        // BigDecimal(buy)).doubleValue();
        Double lucre = sell - buy + commission;
        System.out.println(lucre);
        json.put("sellTotal", sell);
        json.put("buyTotal", buy);
        json.put("commissionTotal", commission);
        json.put("lucre", new BigDecimal(lucre).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return json;
    }
    
    @RequestMapping(value = "/importTbId")
    @ResponseBody
    public String importTbId(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        importTbIdFile(file.getInputStream());
        return "SUCCESS";
    }
    
    private void importTbIdFile(InputStream is)
    {
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
                            tbId = tbId.substring(0, 15) + "119";
                        }
                        if ("953".equals(tbId.substring(12, 15)))
                        {
                            tbId = tbId.substring(0, 15) + "028";
                        }
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
