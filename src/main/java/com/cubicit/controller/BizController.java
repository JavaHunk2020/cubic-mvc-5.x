package com.cubicit.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cubicit.dao.BizDao;

@Controller
public class BizController {
	
	
  @Autowired	
  private BizDao bizDao;
  
//<form action="obiz" method="POST">
	@PostMapping({"/obiz"})
	public String execute(@ModelAttribute Biz biz, Model model){
		Timestamp timestamp=new Timestamp(new Date().getTime());
		biz.setDoe(timestamp);
		bizDao.save(biz);
		//Adding message in request scope so that we can access this message on jsp file
		model.addAttribute("message", "Record is added successfully!!!!!!!!!!!!!!!!!!");
		return "biz"; // ->> /auth.jsp
	}
	
	@GetMapping({"/obiz"})
	public String showPage(){
		return "biz"; // ->> /auth.jsp
	}
	
	
	@PostMapping({"/editBiz"})
	public String updateBiz(HttpServletRequest req,Model model){ //this annotation is reading did as integer from request param
		String name=req.getParameter("name");
		String brand=req.getParameter("brand");
		int did=Integer.parseInt(req.getParameter("did"));
		Biz biz=new Biz();
		biz.setBrand(brand);
		biz.setName(name);
		biz.setId(did);
		//Here we have to update the data inside database
		bizDao.update(biz);
		
		
		model.addAttribute("message", "data is update ");
		
		//Fetch all new records and add inside model to show on JSP
		List<Biz> bizs=bizDao.findAll();
		model.addAttribute("ashma", bizs);
		return "bizs"; // ->> /bizs.jsp
	}
	
	
	@GetMapping({"/editBiz"})
	public String showEditPage(@RequestParam int did,Model model){ //this annotation is reading did as integer from request param
		Biz biz=bizDao.findById(did);
		//model means request scope
		model.addAttribute("pbiz", biz);
		return "ebiz"; // ->> /ebiz.jsp
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
	
	@GetMapping("/deleteCPhoto")
	public String deleteCPhoto(@RequestParam int cid,Model model){
		//deleting the data from the database
		bizDao.deletecPhoto(cid);
		//This is showing remining data from the database
		List<Biz> bizs=bizDao.findAll();
		model.addAttribute("ashma", bizs);
		return "bizs"; // ->> /bizs.jsp
	}
	
	
	
	//http://localhost:8080/cubic-mvc/deleteBiz?name=Mocha
		@GetMapping("/deleteBizId")
		public String deleteById(HttpServletRequest req){
			String dbid=req.getParameter("id");
			//deleting the data from the database
			bizDao.deleteById(Integer.parseInt(dbid));
			//This is showing remining data from the database
			req.setAttribute("message", "data is deleted with db id = "+dbid);
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
	
	//<img src="loadPhoto?dbid=12"/>
	@GetMapping({"/loadPhoto"})
	public void renderPhoto(@RequestParam int dbid,HttpServletResponse resp) throws IOException{
		byte[] photo=bizDao.findPhotoById(dbid);
		resp.setContentType("image/png");
		ServletOutputStream outputStream=resp.getOutputStream(); //reference of the body of the response
		if(photo!=null){
			outputStream.write(photo);
			outputStream.flush();
			outputStream.close();	
		}
	}
	
	@GetMapping({"/cloadPhoto"})
	public void crenderPhoto(@RequestParam int dbid,HttpServletResponse resp) throws IOException{
		byte[] photo=bizDao.cfindPhotoById(dbid);
		resp.setContentType("image/png");
		ServletOutputStream outputStream=resp.getOutputStream(); //reference of the body of the response
		if(photo!=null){
			outputStream.write(photo);
			outputStream.flush();
			outputStream.close();	
		}
	}

}
