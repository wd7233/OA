package rml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.model.CardAmountInfo;
import rml.model.WithdrawName;
import rml.service.WithdrawService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/amountController")
public class AmountController {

    @Autowired
    private WithdrawService withdrawService;

    @RequestMapping(value = "/getAmountList")
    public String getCardInfo(HttpServletRequest request) {
        List<CardAmountInfo> list = withdrawService.getCardAmountSum();
        request.setAttribute("cardAmountList", list);
        return "cardAmountList";
    }


    @RequestMapping(value = "/setWithdraw")
    @ResponseBody
    public String setWithdraw(String name) {
        Integer rsult = withdrawService.withdrawed(name);
        if (rsult.intValue() > 0){
            return "SUCCESS";
        }
        return "ERROR";
    }

    @RequestMapping(value = "/setWithdrawOne")
    @ResponseBody
    public String setWithdrawByShop(String id) {
        Integer rsult = withdrawService.withdrawedById(Integer.parseInt(id));
        if (rsult.intValue() > 0){
            return "SUCCESS";
        }
        return "ERROR";
    }
    @RequestMapping(value = "/setWithdrawAll")
    @ResponseBody
    public String setWithdrawAll() {
        Integer rsult = withdrawService.withdrawedAll();
        if (rsult.intValue() > 0){
            return "SUCCESS";
        }
        return "ERROR";
    }

    @RequestMapping(value = "/userNoList")
    public String userNoList(HttpServletRequest request, String userName) {
//        String param=URLDecoder.decode(param,"UTF-8");
        List<WithdrawName> list = withdrawService.userNoList(userName);
        request.setAttribute("withdrawNameList", list);
        return "cardAmountDetail";
    }

}
