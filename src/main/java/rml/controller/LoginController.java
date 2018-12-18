package rml.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rml.model.GoodType;
import rml.model.Goods;
import rml.model.OrderDetail;
import rml.model.OrderType;
import rml.model.Shop;
import rml.model.Staff;
import rml.service.GoodServiceI;
import rml.service.GoodTypeServiceI;
import rml.service.OrderServiceI;
import rml.service.OrderTypeServiceI;
import rml.service.ShopServiceI;
import rml.service.StaffServiceI;
import rml.util.DateUtil;

@Controller
@RequestMapping("/loginController")
public class LoginController
{
    @Autowired
    private OrderTypeServiceI orderTypeService;
    
    @Autowired
    private StaffServiceI staffService;
    
    @Autowired
    private ShopServiceI shopService;
    
    @Autowired
    private GoodServiceI goodService;
    
    @Autowired
    private OrderServiceI orderService;
    
    @Autowired
    private GoodTypeServiceI goodTypeService;
    
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Staff staff)
    {
        HttpSession session = request.getSession();
        Staff s = staffService.selectByAccountAndPwd(staff.getAccount(), staff.getPwd());
        if (s == null)
        {
            s = (Staff)session.getAttribute("user");
        }
        if (s == null)
        {
            return "";
        }
        session.setAttribute("user", s);
        List<Shop> list = null;
        List<Staff> staffList = null;
        List<GoodType> goodTypeList = goodTypeService.selectAll();
        request.setAttribute("goodTypeList", goodTypeList);
        request.setAttribute("shoplist", list);
        staffList = staffService.selectByRole(s.getId(), s.getRole(),s.getCompanyId());
        request.setAttribute("staffList", staffList);
        // 管理员
        if (s.getRole() == 0)
        {
            return "shopTotalList";
        }
        else if (s.getRole() == 1 || s.getRole() == 3)
        {
            return "shopList";
        }
        // 买手
        else
        {
            List<OrderDetail> orderList = orderService.selectOrderByTime(DateUtil.getStartTime(), DateUtil.getEndTime(), "", 0);
            List<OrderType> orderTypeList = orderTypeService.selectAll();
            request.setAttribute("orderList", orderList);
            request.setAttribute("orderTypeList", orderTypeList);
            return "orderList";
        }
        
    }
    
    @RequestMapping(value = "/regist")
    @ResponseBody
    public String regist(String name, String account, String pwd, Integer role)
    {
        if (role == 0)
        {
            return "";
        }
        Staff user = new Staff();
        user.setName(name);
        user.setAccount(account);
        user.setPwd(pwd);
        user.setRole(role);
        user.setSex(0);
        user.setCreateTime(new Date());
        staffService.insert(user);
        return "SUCCESS";
    }
    
    @RequestMapping(value = "/getShop")
    public String shopList(HttpServletRequest request, Staff staff)
    {
        Staff s = (Staff)request.getSession().getAttribute("user");
        List<Shop> list = new ArrayList<Shop>();
        String keyWord = request.getParameter("keyWord");
        Integer staffId = Integer.parseInt(request.getParameter("staffName"));
        System.out.println(staffId);
        List<Staff> staffList = staffService.selectByRole(s.getId(), s.getRole(),s.getCompanyId());
        if (s.getRole() != 0 && staffId == -1)
        {
            for (Staff sf : staffList)
            {
                List<Shop> ss = shopService.selectByKeyword(sf.getId(), keyWord);
                for (Shop shop : ss)
                {
                    list.add(shop);
                }
            }
        }
        else
        {
            list = shopService.selectByKeyword(staffId, keyWord);
        }
        List<GoodType> goodTypeList = goodTypeService.selectAll();
        request.setAttribute("goodTypeList", goodTypeList);
        request.setAttribute("shoplist", list);
        request.setAttribute("staffList", staffList);
        // 管理员
        if (s.getRole() == 0)
        {
            return "shopTotalList";
        }
        else if (s.getRole() == 1 || s.getRole() == 3)
        {
            return "shopList";
        }
        // 买手
        else
        {
            List<OrderDetail> orderList = orderService.selectOrderByTime(DateUtil.getStartTime(), DateUtil.getEndTime(), "", 0);
            List<OrderType> orderTypeList = orderTypeService.selectAll();
            request.setAttribute("orderList", orderList);
            request.setAttribute("orderTypeList", orderTypeList);
            return "orderList";
        }
    }
    
    @RequestMapping(value = "/updateNums")
    @ResponseBody
    public String updateNums(String shopNumber, Integer num,Integer staffId)
    {
        System.out.println(shopNumber);
        System.out.println(num);
        Shop shop = shopService.getByNumber(shopNumber);
        // System.out.println(shop.toString());
        shop.setNumber(shopNumber.replace("?", ""));
        shop.setGoodsnum(num);
        shop.setStaffId(staffId);
        shopService.updateShop(shop);
        return "SUCCESS";
    }
    
    @RequestMapping(value = "/updateStaffName")
    @ResponseBody
    public String updateStaffName(String shopNumber, Integer staffId)
    {
        System.out.println(shopNumber);
        System.out.println(staffId);
        Shop shop = shopService.getByNumber(shopNumber);
        shop.setNumber(shopNumber.replace("?", ""));
        shop.setStaffId(staffId);
        shopService.updateShop(shop);
        return "SUCCESS";
    }
    
    @RequestMapping(value = "/downLoadGoods")
    public void getRandGoods(HttpServletResponse response, HttpServletRequest request)
    {
        System.out.println("============");
        Integer goodType = Integer.parseInt(request.getParameter("goodType"));
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "goods.txt");
        try
        {
            PrintWriter printWriter = new PrintWriter(response.getOutputStream());
            List<Goods> goodsList = null;
            if (goodType == 1 || goodType == 2)
            {
                goodsList = goodService.selectRandGoods();
            }
            else
            {
                goodsList = goodService.seletcByType(goodType);
            }
            StringBuffer sb = new StringBuffer("");
            for (Goods g : goodsList)
            {
                if (goodType == 1 || goodType == 2)
                {
                    sb.append("http://mobile.yangkeduo.com/goods.html?goods_id=" + g.getGoodsid() + "\r\n");
                }
                else
                {
                    sb.append("https://detail.tmall.com/item.htm?id=" + g.getGoodsid() + "\r\n");
                }
            }
            printWriter.write(sb.toString());
            printWriter.close();
        }
        catch (IOException e)
        {
        }
        return;
    }
    
    @RequestMapping(value = "/downNewLoadGoods")
    public void getNewRandGoods(HttpServletResponse response)
    {
        System.out.println("============");
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "goods.txt");
        try
        {
            PrintWriter printWriter = new PrintWriter(response.getOutputStream());
            List<Goods> goodsList = goodService.seletcByStaffId(1);
            StringBuffer sb = new StringBuffer("");
            for (Goods g : goodsList)
            {
                sb.append("http://mobile.yangkeduo.com/goods.html?goods_id=" + g.getGoodsid() + "\r\n");
            }
            printWriter.write(sb.toString());
            printWriter.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
        }
        return;
    }
    
    @RequestMapping(value = "/loadShops")
    public void loadShops(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("============");
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "shops.txt");
        try
        {
            PrintWriter printWriter = new PrintWriter(response.getOutputStream());
            HttpSession session = request.getSession();
            Staff s = (Staff)session.getAttribute("user");
            List<Shop> shops = shopService.selectByUser(s.getId());
            StringBuffer sb = new StringBuffer("");
            for (Shop ss : shops)
            {
                sb.append(ss.getName() + "----" + ss.getAccount() + "----qichengA1" + "\r\n");
            }
            printWriter.write(sb.toString());
            printWriter.close();
        }
        catch (IOException e)
        {
        }
        return;
    }
}
