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
@RequestMapping("")
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 首页
	 * @param mid
	 * @return
	 */
	/*@RequestMapping
	public ModelAndView index(){
		System.out.println("进来了IndexController index()1 》》》》》》》》》》》》》》》》》》》");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}*/
	


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
