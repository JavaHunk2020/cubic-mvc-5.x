package com.cubicit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerUIController {
	
	@GetMapping("/customers")
	public String showCustomer(){
		return "customers";
	}

}
