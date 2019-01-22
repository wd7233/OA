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
@RequestMapping("/yearController")
public class NewYearController {

    @Autowired
    private WithdrawService withdrawService;

    @RequestMapping(value = "/newYear")
    public String getCardInfo(HttpServletRequest request) {
        return "year/newYear";
    }

}
