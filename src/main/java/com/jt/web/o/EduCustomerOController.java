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

import com.jt.entity.EduCustomer;
import com.jt.service.EduCustomerService;

/**
 * 提升学历控制层
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/o/entercustomer")
public class EduCustomerOController {
	private static final Logger logger = LoggerFactory.getLogger(EduCustomerOController.class);
	
	@Autowired
	private EduCustomerService eduCustomerService;
	
	/**
	 * 用户学历提升测评页面
	 * @param mid
	 * @return
	 */
	@RequestMapping("/enter")
	public ModelAndView applyView(@RequestParam("mid") Integer mid){
		ModelAndView mv = new ModelAndView();
		mv.addObject("mid", mid);
		mv.setViewName("o/enterCustomer");
		return mv;
	}
	
	

	/**
	 * 提交测评后跳转的页面
	 * @param request
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="/enter",method=RequestMethod.POST)
	public ModelAndView apply(HttpServletRequest request, EduCustomer eduCustomer){
		ModelAndView mv = new ModelAndView();
		
		eduCustomerService.apply2(eduCustomer);
		mv.addObject("eduCustomer", eduCustomer);
		mv.setViewName("o/choose3");
		return mv;
	}
	
	/**
	 * 校验手机是否已经存在
	 * @param phone
	 * @return
	 */
	@RequestMapping("/isPhoneExist")
	@ResponseBody
	public boolean isPhoneExist(String phone){
		return eduCustomerService.isPhoneExist(phone);
	}
	
	
}
