package rml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rml.service.MUserServiceI;

@Controller
@RequestMapping("/muserController")
public class MUserController {

	@Autowired
	private MUserServiceI muserService;

//	public MUserServiceI getMuserService() {
//		return muserService;
//	}
//
//	@Autowired
//	public void setMuserService(MUserServiceI muserService) {
//		this.muserService = muserService;
//	}
	
}
