package rml.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rml.model.MUser;
import rml.model.Staff;
import rml.service.StaffServiceI;

@Controller
@RequestMapping("/staffController")
public class StaffController {

	@Autowired
    private StaffServiceI staffService;

//	public MUserServiceI getMuserService() {
//		return muserService;
//	}
//
//	@Autowired
//	public void setMuserService(MUserServiceI muserService) {
//		this.muserService = muserService;
//	}
	
	@RequestMapping(value="/listStaff")
	public String listUser(HttpServletRequest request) {
		
		List <Staff> list = staffService.selectAll();
		request.setAttribute("userlist", list);
		return "listStaff";
	}
}
