package rml.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import rml.model.LucreModel;
import rml.model.OrderDetail;
import rml.model.Shop;
import rml.model.Staff;
import rml.service.OrderServiceI;
import rml.service.ShopServiceI;
import rml.service.StaffServiceI;
import rml.util.DateUtil;
import rml.util.FileUtil;

@Controller
@RequestMapping("/lucreController")
public class LucreController
{
    
    @Autowired
    private StaffServiceI staffService;
    
    @Autowired
    private ShopServiceI shopService;
    
    @Autowired
    private OrderServiceI orderService;
    
    // 订单查询
    @RequestMapping(value = "/getOrderStaff")
    public String getOrderList(HttpServletRequest request)
    {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getStartTime() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getEndTime() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        List<LucreModel> lucreList = new ArrayList<LucreModel>();
        Staff s = (Staff)request.getSession().getAttribute("user");
        List<Staff> staffList = staffService.selectByRole(s.getId(), s.getRole(),s.getCompanyId());
        for (Staff staff : staffList)
        {
            LucreModel lucreM = new LucreModel();
            lucreM.setUserId(staff.getId());
            lucreM.setUserName(staff.getName());
            List<Shop> shopList = shopService.selectByUser(lucreM.getUserId());
            lucreM.setShopList(shopList);
            Double staffLucre = new Double(0);
            Integer orderCnt = 0;
            for (Shop shop : shopList)
            {
                // 所以订单数
                List<OrderDetail> orderTotalList = orderService.selectOrderByTime(start, end, shop.getNumber(), 0);
                // 正常订单-计算利润
                List<OrderDetail> orderList = orderService.selectOrderByTime(start, end, shop.getNumber(), 1);
                Double lucreShop = this.getShopLucre(orderList);
                BigDecimal ddBuy1 = new BigDecimal(String.valueOf(staffLucre));
                BigDecimal ddBuy2 = new BigDecimal(String.valueOf(lucreShop));
                staffLucre = ddBuy1.add(ddBuy2).doubleValue();
                orderCnt += orderTotalList.size();
            }
            // 利润
            lucreM.setLucre(new BigDecimal(staffLucre).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            Integer totalCnt = orderService.selectCountByUser(start, end, staff.getId(), 0);
            Integer proCnt = orderService.selectCountByUser(start, end, staff.getId(), 3);
            Integer backCnt = orderService.selectCountByUser(start, end, staff.getId(), 4);
            Integer editCnt = orderService.selectEditCount(start, end, staff.getId());
            DecimalFormat df1 = new DecimalFormat("0.00%");
            double backCnt_double = backCnt * 1.0;
            // 订单数
            lucreM.setOrderCnt(totalCnt);
            lucreM.setProCnt(proCnt);
            lucreM.setBackCnt(backCnt);
            lucreM.setBackRate(df1.format(backCnt_double / totalCnt) + "");
            lucreM.setEditCnt(editCnt);
            lucreList.add(lucreM);
        }
        System.out.println(lucreList.size());
        request.setAttribute("lucreList", lucreList);
        return "orderStaff";
    }
    
    // 利润计算
    @RequestMapping(value = "/getLucre")
    @ResponseBody
    public JSONObject getLucre(HttpServletRequest request)
    {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Date start = StringUtils.isEmpty(startDate) ? DateUtil.getYesterdayStart() : DateUtil.strToDateLong(startDate, "yy-MM-dd");
        Date end = StringUtils.isEmpty(endDate) ? DateUtil.getYesterdayEnd() : DateUtil.strToDateLong(endDate, "yy-MM-dd");
        JSONObject json = new JSONObject();
        List<OrderDetail> orderList = orderService.selectOrderForLucre(start, end, null);
        Double sell = new Double(0);
        Double buy = new Double(0);
        Double commission = new Double(0);
        for (OrderDetail od : orderList)
        {
            BigDecimal ddSell1 = new BigDecimal(String.valueOf(sell));
            BigDecimal ddSell2 = new BigDecimal(String.valueOf(od.getSellPrice()));
            sell = ddSell1.add(ddSell2).doubleValue();
            BigDecimal ddBuy1 = new BigDecimal(String.valueOf(buy));
            BigDecimal ddBuy2 = new BigDecimal(String.valueOf(od.getBuyPrice()));
            buy = ddBuy1.add(ddBuy2).doubleValue();
            BigDecimal ddCommission1 = new BigDecimal(String.valueOf(commission));
            BigDecimal ddCommission2 = new BigDecimal(String.valueOf(od.getCommission()==null?new Double(0):od.getCommission()));
            commission = ddCommission1.add(ddCommission2).doubleValue();
        }
        System.out.println(sell);
        System.out.println(buy);
        System.out.println(commission);
        Double lucre = sell - buy + commission;
        System.out.println(lucre);
        json.put("sellTotal", sell);
        json.put("buyTotal", buy);
        json.put("commissionTotal", commission);
        json.put("lucre", new BigDecimal(lucre).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return json;
    }
    
    private Double getShopLucre(List<OrderDetail> orderList)
    {
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
        Double lucre = sell - buy;
        System.out.println(lucre);
        return new BigDecimal(lucre).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
