package com.cubicit.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BizController {
	
	
  @Autowired	
  private BizService bizService;
  
//<form action="obiz" method="POST">
	@PostMapping({"/obiz"})
	public String execute(HttpServletRequest req){
		String name=req.getParameter("name");
		String brand=req.getParameter("brand");
		Biz biz=new Biz();
		biz.setBrand(brand);
		biz.setName(name);
		Timestamp timestamp=new Timestamp(new Date().getTime());
		biz.setDoe(timestamp);
		bizService.save(biz);
		//Adding message in request scope so that we can access this message on jsp file
		req.setAttribute("message", "Record is added successfully!!!!!!!!!!!!!!!!!!");
		return "biz"; // ->> /auth.jsp
	}
	
	@GetMapping({"/obiz"})
	public String showPage(){
		return "biz"; // ->> /auth.jsp
	}
	
	

}
