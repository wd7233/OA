package rml.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import rml.model.Express;
import rml.model.SpecialOrder;
import rml.model.SpecialOrderSupple;
import rml.model.Staff;
import rml.service.ExpressServiceI;
import rml.service.SpecialOrderServiceI;
import rml.service.SpecialOrderSuppleServiceI;
import rml.service.StaffServiceI;
import rml.util.DateUtil;
import rml.util.FileUtil;

@Controller
@RequestMapping("/specialorderController")
public class SpecialOrderController
{
    
    @Autowired
    private StaffServiceI staffService;
    
    @Autowired
    private SpecialOrderServiceI specialOsrderService;
    @Autowired
    private SpecialOrderSuppleServiceI specialOrderSuppleService;
    
    @Autowired
    private ExpressServiceI expressService;
    
    // 订单查询-ALL
    @RequestMapping(value = "/getSpecialorder")
    public String getSpecialorder(HttpServletRequest request)
    {
        // 订单查询
        List<SpecialOrder> order = new ArrayList<SpecialOrder>();
        HttpSession session = request.getSession();
        Staff staff = (Staff)session.getAttribute("user");
        List<Staff> staffList = staffService.selectByRole(staff.getId(), staff.getRole(), staff.getCompanyId());
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String afterState = request.getParameter("afterState");
        String orderState = request.getParameter("orderState") == null ? "" : request.getParameter("orderState").trim();
        Integer staffId = request.getParameter("staffId") == null ? staff.getId() : Integer.parseInt(request.getParameter("staffId"));
        String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord").trim();
        if (!StringUtils.isEmpty(keyWord))
        {
            startDate = "2018-01-01";
            endDate = "2021-12-31";
        }
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate, "yy-MM-dd ");
        List<SpecialOrder> orderList = specialOsrderService.selectOrderListByUser(start, end, staffId, keyWord.trim(), orderState, afterState);
        request.setAttribute("orderList", orderList);
        request.setAttribute("staffList", staffList);
        return "specialOrder";
    }
    
    // 订单查询-未发货
    @RequestMapping(value = "/getSendSpecialorder")
    public String getSendSpecialorder(HttpServletRequest request)
    {
        // 订单查询
        HttpSession session = request.getSession();
        Staff staff = (Staff)session.getAttribute("user");
        List<Staff> staffList = staffService.selectByRole(staff.getId(), staff.getRole(), staff.getCompanyId());
        String orderState = "未发货";
        Integer staffId = request.getParameter("staffId") == null ? staff.getId() : Integer.parseInt(request.getParameter("staffId"));
        String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord").trim();
        String startDate = "2018-01-01";
        String endDate = "2021-12-31";
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        List<SpecialOrder> orderList = specialOsrderService.selectOrderListByUser(start, end, staffId, keyWord.trim(), orderState, "无售后或售后取消");
        for (SpecialOrder so : orderList)
        {
            if (so.getWeight() == null)
            {
                so.setWeight("-");
                so.setBaishiPrice("-");
                so.setYouzhengPrice("-");
                so.setShengtongPrice("-");
                so.setAnnengPrice("-");
            }
            else
            {
                // 塞入快递价格查询结果
                //X数量
                //+0.3打包重量
                Double doubleWeight = (Double.parseDouble(so.getWeight()) + 0.3)* so.getCount();
                //SKU 一副的时候
                if (!StringUtils.isEmpty(so.getColor())&&(so.getColor().indexOf("2") != -1 || so.getColor().indexOf("两") != -1))
                {
                    doubleWeight = doubleWeight * 2;
                }
                //
//                if("0.5".equals(doubleWeight+"") || (doubleWeight+"").indexOf(".") == -1 ) 
//                {
//                    so.setWeight(Math.ceil(doubleWeight)+"");
//                }
//                else 
//                {
//                    so.setWeight((doubleWeight+"").substring(0, (doubleWeight+"").length()-2));
//                }
                so.setWeight((int)Math.ceil(doubleWeight)+"");
                Express baishi = expressService.selectPrice(so.getWeight(), so.getProvince(), 0);
                so.setBaishiPrice(baishi == null ? "-" : baishi.getPrice());
                Express youzheng = expressService.selectPrice(so.getWeight(), so.getProvince(), 1);
                so.setYouzhengPrice(youzheng == null ? "-" :youzheng.getPrice());
                Express shengtong = expressService.selectPrice(so.getWeight(), so.getProvince(), 2);
                so.setShengtongPrice(shengtong == null ? "-" : shengtong.getPrice());
                //安能改圆通
                Express anneng = expressService.selectPrice(so.getWeight(), so.getProvince(), 3);
                so.setAnnengPrice(anneng == null ? "-" : anneng.getPrice());
                // 计算价格最低的快递\
                List<Double> expressList = new ArrayList<Double>();
                
                expressList.add("-".equals(so.getBaishiPrice()) ? 999 : Double.parseDouble(so.getBaishiPrice()));
                expressList.add("-".equals(so.getYouzhengPrice()) ? 999 : Double.parseDouble(so.getYouzhengPrice()));
                expressList.add("-".equals(so.getShengtongPrice()) ? 999 : Double.parseDouble(so.getShengtongPrice()));
                expressList.add("-".equals(so.getAnnengPrice()) ? 999 : Double.parseDouble(so.getAnnengPrice()));
                Collections.sort(expressList);
                if (!"-".equals(so.getYouzhengPrice())&&expressList.get(0).compareTo(Double.parseDouble(so.getYouzhengPrice()))==0)
                {
                    so.setExpressPrice("邮政快递");
                }
                else if (!"-".equals(so.getShengtongPrice())&&expressList.get(0).compareTo(Double.parseDouble(so.getShengtongPrice()))==0)
                {
                    so.setExpressPrice("申通快递");
                }
                else if (!"-".equals(so.getAnnengPrice())&&expressList.get(0).compareTo(Double.parseDouble(so.getAnnengPrice()))==0)
                {
                    so.setExpressPrice("圆通快递");
                }
                if (!"-".equals(so.getBaishiPrice())&&expressList.get(0).compareTo(Double.parseDouble(so.getBaishiPrice()))==0)
                {
                    so.setExpressPrice("百世快递");
                }
            }
        }
        request.setAttribute("orderList", orderList);
        request.setAttribute("staffList", staffList);
        return "specialOrderForSend";
    }
    
    // 冒泡排序，获取最便宜的快递
    public static double bubbleSort(double[] numbers)
    {
        double temp = 0;
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++)
        {
            for (int j = 0; j < size - 1 - i; j++)
            {
                if (numbers[j] > numbers[j + 1]) // 交换两数位置
                {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        return numbers[0];
    }
    
    @Transactional
    @RequestMapping(value = "/importOrder")
    @ResponseBody
    public int importOrder(@RequestParam(required = false, value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        List<SpecialOrder> orderList = FileUtil.specialOrderImport(file.getInputStream());
        for (SpecialOrder order : orderList)
        {
            SpecialOrder orderDB = specialOsrderService.selectByOrderId(order.getOrderId());
            if (orderDB == null)
            {
                specialOsrderService.insert(order);
            }
            else
            {
                order.setId(orderDB.getId());
                order.setTelephone(orderDB.getTelephone());
                specialOsrderService.updateByPrimaryKey(order);
            }
        }
        return orderList.size();
    }
    
    @RequestMapping(value = "/getOrderDetailByOrderId")
    @ResponseBody
    public JSONObject getOrderDetailByOrderId(String orderId)
    {
        SpecialOrder order = specialOsrderService.selectByOrderId(orderId);
        JSONObject json = new JSONObject();
        json.put("orderId", order.getOrderId());
        json.put("goodName", order.getGoodName());
        json.put("sku", order.getSku() + "," + order.getColor());
        json.put("count", order.getCount());
        json.put("telephone", order.getTelephone());
        json.put("consignee", order.getConsignee());
        json.put("price", order.getPrice());
        json.put("area", order.getCity() + order.getArea());
        json.put("province", order.getProvince());
        json.put("street", order.getStreet());
        json.put("rmakes", order.getRemakes());
        json.put("message", order.getMessage());
        json.put("proTime", order.getPromiseSendTime());
        json.put("state", order.getState());
        json.put("courierNmuber", order.getCourierNumber());
        json.put("courierCompany", order.getCourierCompany());
        json.put("deliverTime", order.getDeliverTime());
        json.put("afterState", order.getAfterState());
        System.out.println(order.toString());
        System.out.println(order.getOrderId());
        return json;
    }
    
    @RequestMapping(value = "/editOrder")
    @ResponseBody
    public String editOrder(String orderId, String stateN, String consignee, String telephone, String message, String province, String area, String street, String remakes, String courierCompany,
        String courierNumber, String deliverTime)
    {
        SpecialOrder order = specialOsrderService.selectByOrderId(orderId);
        if (order == null)
        {
            return null;
        }
        else
        {
            System.out.println(stateN);
            order.setState(stateN);
            System.out.println(order.getState());
            order.setConsignee(consignee);
            order.setTelephone(telephone);
            order.setMessage(message);
            order.setProvince(province);
            order.setCity(area.split("市")[0] + "市");
            order.setArea(area.split("市")[1]);
            order.setStreet(street);
            order.setRemakes(remakes);
            order.setCourierCompany(courierCompany);
            order.setCourierNumber(courierNumber);
            order.setDeliverTime(StringUtils.isEmpty(deliverTime) ? null : DateUtil.strToDateLong(deliverTime, "yy-MM-dd HH:mm"));
            specialOsrderService.updateByPrimaryKey(order);
            return "SUCCESS";
        }
    }
    //补发录入
    @RequestMapping(value = "/suppleOrder")
    @ResponseBody
    public String suppleOrder(String orderId, String priceAdd, String messageAdd)
    {
        SpecialOrderSupple sos = new SpecialOrderSupple();
            sos.setOrderId(orderId);
            sos.setSupplePrice(Double.parseDouble(priceAdd));
            sos.setMessage(messageAdd);
            sos.setCreateTime(new Date());
            sos.setState(0);
            specialOrderSuppleService.insert(sos);
            return "SUCCESS";
    }
    // 发货，只更新发货信息
    @RequestMapping(value = "/sendOrder")
    @ResponseBody
    public String sendOrder(String orderId, String stateN, String consignee, String telephone, String message, String province, String area, String street, String remakes, String courierCompany,
        String courierNumber, String deliverTime)
    {
        SpecialOrder order = specialOsrderService.selectByOrderId(orderId);
        if (order == null)
        {
            return null;
        }
        else
        {
            order.setCourierCompany(courierCompany);
            order.setCourierNumber(courierNumber);
            order.setDeliverTime(StringUtils.isEmpty(deliverTime) ? null : DateUtil.strToDateLong(deliverTime, "yy-MM-dd HH:mm"));
            specialOsrderService.updateByPrimaryKey(order);
            return "SUCCESS";
        }
        
    }
    // 快递价格查询
    @RequestMapping(value = "/selectExpress")
    @ResponseBody
    public JSONObject selectExpress(String weight, String province)
    {
        JSONObject json = new JSONObject();
        Express baishiExpress = expressService.selectPrice(weight, province, 0);
        Express youzhengExpress = expressService.selectPrice(weight, province, 1);
        Express shengtongExpress = expressService.selectPrice(weight, province, 2);
        Express annengExpress = expressService.selectPrice(weight, province, 3);
        json.put("baishi", baishiExpress==null?"-":baishiExpress.getPrice());
        json.put("youzheng", youzhengExpress==null?"-":(int)Math.ceil(Double.parseDouble(youzhengExpress.getPrice()) * 0.8 )+"");
        json.put("shengtong", shengtongExpress==null?"-":shengtongExpress.getPrice());
        json.put("anneng", annengExpress==null?"-":annengExpress.getPrice());
        return json;
    }
}
