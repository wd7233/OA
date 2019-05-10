package rml.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import rml.model.CmzDanmu;
import rml.model.OrderCommission;
import rml.model.OrderDetail;
import rml.model.Shop;
import rml.model.Staff;
import rml.service.CmzServiceI;

/**
 * 褚梦泽生日快乐~
 * 
 * @author pamchen-1
 * 
 */
@Controller
@RequestMapping(value = {"Cmz"})
public class CmzContorller
{
    @Autowired
    private CmzServiceI cmzService;
    
    @RequestMapping(value = "/Hello")
    public String Hello(HttpServletRequest request, Staff staff)
    {
        return "Cmz/cmz";
    }
    
    @RequestMapping(value = "/Wxzf")
    public String Wxzf(HttpServletRequest request, Staff staff)
    {
        List<CmzDanmu> danmuList = cmzService.selectAll();
        request.setAttribute("danmuList", danmuList);
        request.setAttribute("danmu", 123456);
        return "Cmz/wxhd3";
    }
    
    @RequestMapping(value = "/test")
    public String Test(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        List<CmzDanmu> danmuList = cmzService.selectAll();
        request.setAttribute("danmuList", danmuList);
        return "Cmz/test";
        
    }
    
    // 编辑订单
    @RequestMapping(value = "/getDanmu")
    @ResponseBody
    public JSONObject getDanmu()
    {
        JSONObject js = new JSONObject();
        List<CmzDanmu> danmuList = cmzService.selectTenMin();
        if(danmuList== null || danmuList.size() == 0) 
        {
            danmuList =  cmzService.selectRand();
        }
        else 
        {
            System.out.println("=================================");
            CmzDanmu cdm = danmuList.get(0);
            System.out.println(cdm.getIsPass());
            cdm.setIsPass(3);
            cmzService.updateByPrimaryKey(cdm);
            System.out.println("=================================");

            
        }
        js.put("danmu", danmuList.get(0).getContent());
        return js;
    }
    @RequestMapping(value = "/fabu")
    @ResponseBody
    public String fabu(Integer id,Integer isPass)
    {
        CmzDanmu cdm = new CmzDanmu();
        cdm.setIsPass(isPass);
        cdm.setId(id);
        cmzService.updateByPrimaryKey(cdm);
        return "SUCCESS";
    }
}
