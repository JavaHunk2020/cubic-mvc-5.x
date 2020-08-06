package com.cubicit.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cubicit.dao.BizDao;

@Controller
public class BizController {
	
	
  @Autowired	
  private BizDao bizDao;
  
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
		bizDao.save(biz);
		//Adding message in request scope so that we can access this message on jsp file
		req.setAttribute("message", "Record is added successfully!!!!!!!!!!!!!!!!!!");
		return "biz"; // ->> /auth.jsp
	}
	
	@GetMapping({"/obiz"})
	public String showPage(){
		return "biz"; // ->> /auth.jsp
	}
	
	//http://localhost:8080/cubic-mvc/deleteBiz?name=Mocha
	@GetMapping("/deleteBiz")
	public String deleteBizLogic(HttpServletRequest req){
		String pname=req.getParameter("name");
		//deleting the data from the database
		bizDao.deleteByName(pname);
		
		//This is showing remining data from the database
		req.setAttribute("message", "data is deleted with name = "+pname);
		List<Biz> bizs=bizDao.findAll();
		req.setAttribute("ashma", bizs);
		return "bizs"; // ->> /bizs.jsp
	}
	
	
	
	@GetMapping({"/showBizs"})
	public String showBizsData(HttpServletRequest req){
		List<Biz> bizs=bizDao.findAll();
		req.setAttribute("ashma", bizs);
		return "bizs"; // ->> /bizs.jsp
	}

}
