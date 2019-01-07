package rml.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import rml.model.*;
import rml.service.*;
import rml.util.DateUtil;
import rml.util.FileUtil;

@Controller
@RequestMapping("/shopController")
public class ShopController
{
    
    @Autowired
    private ShopServiceI shopService;
    
    @Autowired
    private StaffServiceI staffService;
    
    @Autowired
    private OrderServiceI orderService;
    
    @Autowired
    private CardInfoServiceI cardInfoService;

    @Autowired
    private WithdrawService withdrawService;
    
    @RequestMapping(value = "/getShopList")
    public String getShopList(HttpServletRequest request)
    {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String orderId = request.getParameter("orderId");
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getStartTime() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getEndTime() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        // List<OrderDetail> orderList =
        // orderService.selectOrderByTime(start,end,orderId);
        // request.setAttribute("orderList", orderList);
        return "orderList";
    }
    
    @RequestMapping(value = "/getShop")
    public String shopList(HttpServletRequest request)
    {
        Staff s = (Staff)request.getSession().getAttribute("user");
        List<Shop> list = null;
        String keyWord = request.getParameter("keyWord")==null?"": request.getParameter("keyWord").trim();
        Integer staffId = request.getParameter("staffName") == null ? s.getId() : Integer.parseInt(request.getParameter("staffName"));;
        list = shopService.selectByKeyword(staffId,keyWord);
        request.setAttribute("shoplist", list);
        List<Staff> staffList = staffService.selectByRole(s.getId(), s.getRole(),s.getCompanyId());
        request.setAttribute("staffList", staffList);
        return "shopListForBuy";
    }
    
    @RequestMapping(value = "/goodsLower")
    public void loadShops(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("============");
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "goodsLower.txt");
        try
        {
            PrintWriter printWriter = new PrintWriter(response.getOutputStream());
            HttpSession session = request.getSession();
            Staff s = (Staff)session.getAttribute("user");
            List<Shop> shops = shopService.selectByUser(s.getId());
            StringBuffer sb = new StringBuffer("");
            for (Shop ss : shops)
            {
                sb.append(ss.getAccount() + "----qichengA1" + "\r\n");
            }
            printWriter.write(sb.toString());
            printWriter.close();
        }
        catch (IOException e)
        {
        }
        return;
    }
    @RequestMapping(value = "/getCardInfo")
    public String getCardInfo(HttpServletRequest request)
    {
        Staff s = (Staff)request.getSession().getAttribute("user");
        List<CardInfo> list = null;
        Integer staffId = s.getId();
        list = cardInfoService.selectByUserId(staffId);
        request.setAttribute("cardlist", list);
        return "shopCardInfoList";
    }

    @RequestMapping(value = "/saveAmount")
    @ResponseBody
    public String saveAmount(HttpServletRequest request,Double amount,String shopNumber) {
        Staff s = (Staff)request.getSession().getAttribute("user");
        Withdraw withdraw = new Withdraw();
        withdraw.setStaffId(s.getId());
        withdraw.setAmount(amount);
        withdraw.setShopNumber(shopNumber);
        Integer result = withdrawService.save(withdraw);
        if (result.intValue() > 0){
            return "SUCCESS";
        }
        return "ERROR";
    }
}
