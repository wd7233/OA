package rml.controller;

import java.io.IOException;
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

import rml.model.SpecialOrder;
import rml.model.Staff;
import rml.service.SpecialOrderServiceI;
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
        String orderState = request.getParameter("orderState") == null ? "" : request.getParameter("orderState").trim();
        Integer staffId = request.getParameter("staffId") == null ? staff.getId() : Integer.parseInt(request.getParameter("staffId"));
        String keyWord = request.getParameter("keyWord") == null ? "" : request.getParameter("keyWord").trim();
        if (!StringUtils.isEmpty(keyWord))
        {
            startDate = "2018-01-01";
            endDate = "2021-12-31";
        }
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        List<SpecialOrder> orderList = specialOsrderService.selectOrderListByUser(start, end, staffId, keyWord.trim(), orderState);
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
        List<SpecialOrder> orderList = specialOsrderService.selectOrderListByUser(start, end, staffId, keyWord.trim(), orderState);
        request.setAttribute("orderList", orderList);
        request.setAttribute("staffList", staffList);
        return "specialOrderForSend";
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
    //发货，只更新发货信息
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
}
