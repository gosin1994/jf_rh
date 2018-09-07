package com.jt.web.o;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jt.entity.Customer;
import com.jt.service.CustomerService;

@Controller
@RequestMapping("/o/customer")
public class CustomerOController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerOController.class);
	
	@Autowired
	private CustomerService customerService;
	
	
	/**
	 * 官网提交测评后跳转的页面
	 * @param request
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/apply",method=RequestMethod.POST)
	public boolean apply(HttpServletRequest request, Customer customer,@RequestParam(value="mid",defaultValue="0") Integer mid){
		System.out.println("进来了》》》》》》》》》》》");
		customer.setMemberId(mid);
		System.out.println("mid==============>"+mid);
		System.out.println(">>>>>>>>>>>>>"+customerService.apply(customer));
		return customerService.apply(customer);
	}
	
	/**
	 * 校验手机是否已经存在
	 * @param phone
	 * @return
	 */
	@RequestMapping("/isPhoneExist")
	@ResponseBody
	public boolean isPhoneExist(String phone){
		return customerService.isPhoneExist(phone);
	}
	
}
