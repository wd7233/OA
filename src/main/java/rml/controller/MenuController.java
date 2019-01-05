package rml.controller;

import rml.model.Menu;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther wudi
 * @description
 **/
@Controller
@RequestMapping("/menuController")
public class MenuController {

	@RequestMapping(value = "/getMenu")
	@ResponseBody
	public List<Menu> getMenu(){
		List<Menu> list = new ArrayList<Menu>();
		Menu menu = new Menu();
		menu.setMenu("aaaaaaaaaaa");
		menu.setId(1);
		menu.setUrl("/amountController/getAmountList.do");
		menu.setLevel(2);
		menu.setSupMenuId(3);
		Menu menu1 = new Menu();
		menu1.setMenu("bbbbbbbbbbbbb");
		menu1.setId(2);
		menu1.setUrl("www.baidu.com");
		menu1.setLevel(2);
		menu1.setSupMenuId(3);
		Menu menu2 = new Menu();
		menu2.setMenu("ccccccccccccc");
		menu2.setId(3);
		menu2.setLevel(1);
		list.add(menu);
		list.add(menu1);
		list.add(menu2);
		return list;
	}
}