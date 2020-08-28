package com.cubicit.controller.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubicit.controller.vo.ApplicationResponseVO;
import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.dao.CustomerDao;

@RestController
//this annotation ensures all the method inside will generate
//raw data ->>JSON,XML etc
@RequestMapping("/v3")
public class CustomerController {
	
	@Autowired
	private CustomerDao customerDao;
	
	//{"name":"James King","email":"mocha@gmail.com","debitcard":null,"valid":null,"cvv":0,
	 //"mobile":"9999762622","photo":null,"dbphoto":null,"age":12,"company":"HCL"}
     //}
	//@RequestBody - reading the raw data from incoming request and creating an instance of CustomerVO
	// and finally populating this raw data into this object
	@PostMapping("/customers")
	public ApplicationResponseVO createCustomer(@RequestBody CustomerVO customerVO){
		System.out.println(customerVO);
		customerDao.save(customerVO);
		//We will save it into database
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		applicationResponseVO.setStatus("200");
		applicationResponseVO.setMessage("customer is added email = "+customerVO.getEmail());
		return applicationResponseVO;
	}
	
	@PostMapping("/ccustomer")
	public ApplicationResponseVO ccreateCustomer(@RequestBody CustomerVO customerVO){
		System.out.println(customerVO);
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		applicationResponseVO.setStatus("200");
		applicationResponseVO.setMessage("customer is added email = "+customerVO.getEmail());
		return applicationResponseVO;
	}
	
	@GetMapping(value="/customers",params={"cid"})
	public CustomerVO getCustomerByCid(@RequestParam int  cid){
		CustomerVO customerVO=customerDao.findByCid(cid);
		return customerVO; //view resolver is not coming in pic
	}
	
	@GetMapping(value="/customers",params={"email"})
	public CustomerVO getCustomer(@RequestParam String email){
		CustomerVO customerVO=new CustomerVO();
		customerVO.setAge(12);
		customerVO.setCompany("HCL");
		customerVO.setEmail(email);
		customerVO.setMobile("9999762622");
		customerVO.setName("James King");
		return customerVO; //view resolver is not coming in pic
	}
	
	@GetMapping("/customers")
	public List<CustomerVO> getCustomers(){
		List<CustomerVO> customerVOs = customerDao.findAll();
		return customerVOs; //view resolver is not coming in pic
	}

}
